package services;

import dao.MarksDAO;
import models.Teacher;

import java.util.Scanner;

public class TeacherService {

    private MarksDAO marksDAO = new MarksDAO();
    private Scanner scanner = new Scanner(System.in);

    public void handleTeacher(Teacher teacher) {

        int choice;

        do {
            teacher.showMenu();
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    // Add Marks
                    System.out.print("Enter Student ID: ");
                    int studentId = scanner.nextInt();

                    scanner.nextLine(); // clear buffer

                    System.out.print("Enter Subject: ");
                    String subject = scanner.nextLine();

                    System.out.print("Enter Marks: ");
                    int marks = scanner.nextInt();

                    marksDAO.addMarks(studentId, subject, marks, teacher.getId());
                    break;

                case 2:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 2);
    }
}