import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private Connection connection;

    public DBManager(String dbDriver, String dbHost, String dbPort, String dbName, String dbUser, String dbPassword) {
        try {
            Class.forName(dbDriver);
            this.connection = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName, dbUser, dbPassword);
        } catch (SQLException e) {
            System.out.println("Error connecting to database - " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found.");
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}