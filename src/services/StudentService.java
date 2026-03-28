package services;

import dao.MarksDAO;
import models.Student;

import java.util.Scanner;

public class StudentService {

    private MarksDAO marksDAO = new MarksDAO();
    private Scanner scanner = new Scanner(System.in);

    public void handleStudent(Student student) {

        int choice = 0;

        do {
            try {
                student.showMenu();
                System.out.print("Enter choice: ");

                String input = scanner.nextLine();

                // Validate input
                if (input.isEmpty()) {
                    throw new IllegalArgumentException("Input cannot be empty!");
                }

                choice = Integer.parseInt(input);

                switch (choice) {

                    case 1:
                        try {
                            // View Marks
                            marksDAO.getMarksByStudent(student.getId());
                        } catch (Exception e) {
                            System.out.println("Error fetching marks: " + e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("Logging out...");
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid choice! Please enter 1 or 2.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            } catch (IllegalArgumentException e) {
                System.out.println("Error" + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }

        } while (choice != 2);
    }
}