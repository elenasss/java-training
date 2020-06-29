package gasStation;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class DBManager {

    private static DBManager instance;
    private static Connection connection;
    private static final String DB_IP = "127.0.0.1";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "gas_station";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "MyPass_abv8";

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME, DB_USER, DB_PASS);
        } catch (SQLException throwables) {
            System.out.println("Error connecting to database");
        }
    }

    public void insertIntoDB(Car.FuelType fuelType, int fuelVolume, int kolonka, LocalDateTime date) {
        String insertQuery = "INSERT INTO loadings (fuel_type, fuel_quantity, kolonka_id, loading_time) VALUES(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, fuelType.toString());
            preparedStatement.setInt(2, fuelVolume);
            preparedStatement.setInt(3, kolonka);
            preparedStatement.setTimestamp(4, Timestamp.valueOf(date));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //statics:
    public void getAllLoadings() {
        StringBuilder report = new StringBuilder();
        String selectQuery = "SELECT kolonka_id, fuel_type, fuel_quantity, loading_time FROM loadings";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            ResultSet result = preparedStatement.executeQuery();
            Map<String, TreeSet<Loading>> loadings = new TreeMap<>();
            while (result.next()) {
                String kolonka = result.getString("kolonka_id");
                Loading loading = new Loading(
                        kolonka,
                        result.getString("fuel_type"),
                        result.getInt("fuel_quantity"),
                        result.getTimestamp("loading_time").toLocalDateTime()
                );
                if (!loadings.containsKey(kolonka)) {
                    loadings.put(kolonka, new TreeSet<>());
                }
                loadings.get(kolonka).add(loading);
            }
            result.close();

            for (Map.Entry<String, TreeSet<Loading>> entry : loadings.entrySet()) {
                String reportText = "Kolonka " + entry.getKey();
                report.append(reportText + "\n");
                for (Loading loadingStatistic : entry.getValue()) {
                    report.append(loadingStatistic + "\n");
                }
            }
            GasStation.writeReportsInFile(report.toString(), 1);
        } catch (SQLException e) {
            System.out.println("ops " + e.getMessage());
        }
    }

    public void getAllLoadedCars() {
        String sql = "SELECT kolonka_id, COUNT(fuel_type) AS fuels FROM loadings WHERE DATE(loading_time) = CURDATE() GROUP BY kolonka_id";
        StringBuilder reportForFile = new StringBuilder();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                reportForFile.append("Kolonka " + result.getString("kolonka_id")
                        + " - Cars: " + result.getInt("fuels") + "\n");
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        GasStation.writeReportsInFile(reportForFile.toString(), 2);
    }

    public void getLoadedFuelByType() {
        String sql = "SELECT fuel_type AS Fuel_types, SUM(fuel_quantity) AS Liters FROM loadings GROUP BY fuel_type";
        StringBuilder report = new StringBuilder();
        try (PreparedStatement pState = connection.prepareStatement(sql)) {
            ResultSet result = pState.executeQuery();
            while (result.next()) {
                report.append("Fuel " + result.getString("Fuel_types") + " - Quantity: " + result.getInt("Liters") + "\n");
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        GasStation.writeReportsInFile(report.toString(), 3);
    }

    public void getProfit() {
        String sql = "SELECT fuel_type AS Fuel_types, SUM(fuel_quantity) AS Liters FROM loadings GROUP BY fuel_type";
        StringBuilder report = new StringBuilder();
        try (PreparedStatement pState = connection.prepareStatement(sql)) {
            ResultSet result = pState.executeQuery();
            while (result.next()) {
                String fuelType = result.getString("Fuel_types");
                int quantity = result.getInt("Liters");
                double sum = checkFuel(fuelType) * quantity;
                report.append("Fuel " + fuelType + " - Profit: " + sum + "\n");
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        GasStation.writeReportsInFile(report.toString(), 4);
    }

    private double checkFuel(String fuelType) {
        double price = 0;
        switch (fuelType) {
            case "DISEL":
                price = 2.40;
                break;
            case "GAS":
                price = 1.60;
                break;
            case "PETROL":
                price = 2.0;
                break;
        }
        return price;
    }
}
