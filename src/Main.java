import dao.UserDAO;
import database.CreateTables;
import models.*;
import services.StudentService;
import services.TeacherService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CreateTables.createTables();

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        StudentService studentService = new StudentService();
        TeacherService teacherService = new TeacherService();

        int choice;

        do {
            System.out.println("\n===== Student Marksheet System =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    // Register
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Role (student/teacher/principal): ");
                    String role = scanner.nextLine();

                    System.out.print("Enter Username: ");
                    String username = scanner.nextLine();

                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();

                    userDAO.registerUser(name, role, username, password);
                    break;

                case 2:
                    // Login
                    System.out.print("Enter Username: ");
                    String loginUser = scanner.nextLine();

                    System.out.print("Enter Password: ");
                    String loginPass = scanner.nextLine();

                    User user = userDAO.login(loginUser, loginPass);

                    if (user == null) {
                        System.out.println("Invalid credentials!");
                    } else {
                        System.out.println("Welcome " + user.getName());

                        // Role-based routing
                        if (user instanceof Student) {
                            studentService.handleStudent((Student) user);

                        } else if (user instanceof Teacher) {
                            teacherService.handleTeacher((Teacher) user);

                        } else if (user instanceof Principal) {
                            System.out.println("Principal panel coming soon...");
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 3);

        scanner.close();
    }
}