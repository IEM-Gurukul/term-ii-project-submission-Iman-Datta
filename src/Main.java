import database.CreateTables;

public class Main {
    public static void main(String[] args) {

        System.out.println("===== Student Marksheet System =====");

        // Step 1: Create tables (if not exist)
        CreateTables.createTables();

        // Step 2: System ready
        System.out.println("System is ready to use!");
    }
}