package database;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTables {

    public static void createTables() {

        String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "role TEXT NOT NULL, " +
                "username TEXT UNIQUE NOT NULL, " +
                "password TEXT NOT NULL" +
                ");";

        String createMarksTable = "CREATE TABLE IF NOT EXISTS marks (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "student_id INTEGER, " +
                "subject TEXT, " +
                "marks INTEGER, " +
                "teacher_id INTEGER, " +
                "FOREIGN KEY(student_id) REFERENCES users(id), " +
                "FOREIGN KEY(teacher_id) REFERENCES users(id)" +
                ");";

        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();

            stmt.execute(createUsersTable);
            stmt.execute(createMarksTable);

            System.out.println("Tables created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}