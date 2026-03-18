package models;

public class Student extends User {

    // Constructor
    public Student(int id, String name, String role, String username, String password) {
        super(id, name, role, username, password);
    }

    // Implement abstract method
    @Override
    public void showMenu() {
        System.out.println("----- Student Menu -----");
        System.out.println("1. View Profile");
        System.out.println("2. View Marks");
        System.out.println("3. View Attendance");
        System.out.println("4. Logout");
    }
}