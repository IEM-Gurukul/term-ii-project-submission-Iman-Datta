package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:sqlite:src/database/school.db";

    public static Connection getConnection() {
        try {
            System.out.println("Database connected");
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
            return null;
        }
    }
}