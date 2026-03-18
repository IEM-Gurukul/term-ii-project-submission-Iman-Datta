package models;

public class Teacher extends User {

    // Constructor
    public Teacher(int id, String name, String role, String username, String password) {
        super(id, name, role, username, password);
    }

    // Implement abstract method
    @Override
    public void showMenu() {
        System.out.println("----- Teacher Menu -----");
        System.out.println("1. View Profile");
        System.out.println("2. Add Marks");
        System.out.println("3. Take Attendance");
        System.out.println("4. Logout");
    }
}