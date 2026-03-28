package services;

import dao.MarksDAO;
import models.Teacher;

import java.util.Scanner;

public class TeacherService {

    private MarksDAO marksDAO = new MarksDAO();
    private Scanner scanner = new Scanner(System.in);

    public void handleTeacher(Teacher teacher) {

        int choice = 0;

        do {
            try {
                teacher.showMenu();
                System.out.print("Enter choice: ");

                String input = scanner.nextLine();

                if (input.isEmpty()) {
                    throw new IllegalArgumentException("Choice cannot be empty!");
                }

                choice = Integer.parseInt(input);

                switch (choice) {

                    case 1:
                        try {
                            // Add Marks

                            System.out.print("Enter Student ID: ");
                            String studentInput = scanner.nextLine();

                            if (studentInput.isEmpty()) {
                                throw new IllegalArgumentException("Student ID cannot be empty!");
                            }

                            int studentId = Integer.parseInt(studentInput);

                            System.out.print("Enter Subject: ");
                            String subject = scanner.nextLine();

                            if (subject.isEmpty()) {
                                throw new IllegalArgumentException("Subject cannot be empty!");
                            }

                            System.out.print("Enter Marks: ");
                            String marksInput = scanner.nextLine();

                            int marks = Integer.parseInt(marksInput);

                            // Logical validation
                            if (marks < 0 || marks > 100) {
                                throw new IllegalArgumentException("Marks must be between 0 and 100!");
                            }

                            marksDAO.addMarks(studentId, subject, marks, teacher.getId());

                            System.out.println("Marks added successfully!");

                        } catch (NumberFormatException e) {
                            System.out.println("Please enter valid numeric values!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error" + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Error while adding marks: " + e.getMessage());
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