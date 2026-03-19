package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DBConnection;

public class MarksDAO {

    // Add Marks (Teacher use)
    public void addMarks(int studentId, String subject, int marks, int teacherId) {

        String query = "INSERT INTO marks (student_id, subject, marks, teacher_id) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, studentId);
            stmt.setString(2, subject);
            stmt.setInt(3, marks);
            stmt.setInt(4, teacherId);

            stmt.executeUpdate();
            System.out.println("Marks added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Marks (Student use)
    public void getMarksByStudent(int studentId) {

        String query = "SELECT subject, marks FROM marks WHERE student_id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, studentId);

            ResultSet rs = stmt.executeQuery();

            System.out.println("----- Your Marks -----");

            boolean found = false;

            while (rs.next()) {
                found = true;

                String subject = rs.getString("subject");
                int marks = rs.getInt("marks");

                System.out.println(subject + " : " + marks);
            }

            if (!found) {
                System.out.println("No marks found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}