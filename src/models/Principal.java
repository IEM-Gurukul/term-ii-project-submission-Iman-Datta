package models;

public class Principal extends User {

    // Default credentials (used when creating first principal)
    public static final String DEFAULT_USERNAME = "principal";
    public static final String DEFAULT_PASSWORD = "admin123";

    // Constructor (USED when loading from DB)
    public Principal(int id, String name, String role, String username, String password) {
        super(id, name, role, username, password);
    }

    // Optional constructor (USED when creating default principal)
    public Principal(int id, String name) {
        super(id, name, "principal", DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    @Override
    public void showMenu() {
        System.out.println("----- Principal Menu -----");
        System.out.println("1. View All Students");
        System.out.println("2. View All Teachers");
        System.out.println("3. Manage System");
        System.out.println("4. Logout");
    }
}