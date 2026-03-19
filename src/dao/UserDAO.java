package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DBConnection;
import models.*;

public class UserDAO {

    // Register user
    public void registerUser(String name, String role, String username, String password) {

        // Prevent multiple principals
        if (role.equalsIgnoreCase("principal") && principalExists()) {
            System.out.println("Principal already exists! Cannot create another.");
            return;
        }

        String query = "INSERT INTO users (name, role, username, password) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, name);
            stmt.setString(2, role);
            stmt.setString(3, username);
            stmt.setString(4, password);

            stmt.executeUpdate();
            System.out.println("User registered successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Login user
    public User login(String username, String password) {

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String role = rs.getString("role");

                // Create object based on role
                if (role.equalsIgnoreCase("student")) {
                    return new Student(id, name, role, username, password);

                } else if (role.equalsIgnoreCase("teacher")) {
                    return new Teacher(id, name, role, username, password);

                } else if (role.equalsIgnoreCase("principal")) {
                    // FIXED CONSTRUCTOR
                    return new Principal(id, name, role, username, password);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // login failed
    }

    // Check if principal already exists
    public boolean principalExists() {

        String query = "SELECT COUNT(*) FROM users WHERE role = 'principal'";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}