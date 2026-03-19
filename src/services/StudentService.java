package services;

import dao.MarksDAO;
import models.Student;

import java.util.Scanner;

public class StudentService {

    private MarksDAO marksDAO = new MarksDAO();
    private Scanner scanner = new Scanner(System.in);

    public void handleStudent(Student student) {

        int choice;

        do {
            student.showMenu();
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    // View Marks
                    marksDAO.getMarksByStudent(student.getId());
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