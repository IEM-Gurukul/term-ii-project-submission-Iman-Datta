package models;

public abstract class User {

    protected int id;
    protected String name;
    protected String role;
    protected String username;
    protected String password;

    // Constructor
    public User(int id, String name, String role, String username, String password){
        this.id = id;
        this.name = name;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Abstract method
    public abstract void showMenu();
}