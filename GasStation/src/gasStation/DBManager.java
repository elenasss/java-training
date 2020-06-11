package gasStation;

import java.sql.*;

public class DBManager {

    private static DBManager instance;
    private static Connection connection;
    private static final String DB_IP = "127.0.0.1";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "gasStation.GasStation";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "MyPass";

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME, DB_USER, DB_PASS);
        } catch (SQLException throwables) {
            System.out.println("Error connecting to database");
        }
        try {
            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Error connecting to database");
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
