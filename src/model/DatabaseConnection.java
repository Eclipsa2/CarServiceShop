package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // static instance of the singleton class
    private static DatabaseConnection instance = null;
    // JDBC connection
    private Connection connection;

    // private constructor to prevent instantiation from outside the class
    private DatabaseConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/serviceVeriku";
        String username = "root";
        String password = "";
        this.connection = DriverManager.getConnection(url, username, password);
    }

    // public static method to get the singleton instance
    public static DatabaseConnection getInstance() throws SQLException {
        // create a new instance if one does not exist
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // public method to get the JDBC connection
    public Connection getConnection() {
        return this.connection;
    }
}
