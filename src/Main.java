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

        int choice = 0;

        do {
            try {
                System.out.println("\n===== Student Marksheet System =====");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");

                String input = scanner.nextLine();

                if (input.isEmpty()) {
                    throw new IllegalArgumentException("Choice cannot be empty");
                }

                choice = Integer.parseInt(input);

                switch (choice) {

                    case 1:
                        try {
                            System.out.print("Enter Name: ");
                            String name = scanner.nextLine();

                            if (name.isEmpty()) {
                                throw new IllegalArgumentException("Name cannot be empty");
                            }

                            System.out.print("Enter Role (student/teacher/principal): ");
                            String role = scanner.nextLine().toLowerCase();

                            if (!(role.equals("student") || role.equals("teacher") || role.equals("principal"))) {
                                throw new IllegalArgumentException("Invalid role entered");
                            }

                            System.out.print("Enter Username: ");
                            String username = scanner.nextLine();

                            if (username.isEmpty()) {
                                throw new IllegalArgumentException("Username cannot be empty");
                            }

                            System.out.print("Enter Password: ");
                            String password = scanner.nextLine();

                            if (password.isEmpty()) {
                                throw new IllegalArgumentException("Password cannot be empty");
                            }

                            userDAO.registerUser(name, role, username, password);
                            System.out.println("Registration successful");

                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Error during registration: " + e.getMessage());
                        }
                        break;

                    case 2:
                        try {
                            System.out.print("Enter Username: ");
                            String loginUser = scanner.nextLine();

                            System.out.print("Enter Password: ");
                            String loginPass = scanner.nextLine();

                            if (loginUser.isEmpty() || loginPass.isEmpty()) {
                                throw new IllegalArgumentException("Username or Password cannot be empty");
                            }

                            User user = userDAO.login(loginUser, loginPass);

                            if (user == null) {
                                throw new IllegalArgumentException("Invalid credentials");
                            }

                            System.out.println("Welcome " + user.getName());

                            if (user instanceof Student) {
                                studentService.handleStudent((Student) user);

                            } else if (user instanceof Teacher) {
                                teacherService.handleTeacher((Teacher) user);

                            } else if (user instanceof Principal) {
                                System.out.println("Principal panel coming soon...");
                            }

                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Error during login: " + e.getMessage());
                        }
                        break;

                    case 3:
                        System.out.println("Exiting system");
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid choice. Enter 1, 2 or 3");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }

        } while (choice != 3);

        scanner.close();
    }
}