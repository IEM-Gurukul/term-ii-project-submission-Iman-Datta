package models;

public class Principal extends User {

    // Predefined credentials
    private static final String DEFAULT_USERNAME = "principal";
    private static final String DEFAULT_PASSWORD = "admin123";

    // Constructor (no need to pass username/password)
    public Principal(int id, String name) {
        super(id, name, "principal", DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    // Implement abstract method
    @Override
    public void showMenu() {
        System.out.println("----- Principal Menu -----");
        System.out.println("1. View All Students");
        System.out.println("2. View All Teachers");
        System.out.println("3. Manage System");
        System.out.println("4. Logout");
    }
}