package ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Scanner;
import java.util.Set;

import models.*;


public class ConsoleUI {
    private Scanner input = new Scanner(System.in);

    public int promptMenuChoice(String prompt, int max) {
        System.out.print(prompt);
        int choice = input.nextInt();
        while (choice < 1 || choice > max) {
            System.out.printf("Invalid Selection: Enter a number from 1 - %d.\n", max);
            System.out.print("Enter your selection to continue: ");
            choice = input.nextInt();
        }
        input.nextLine();
        return choice;
    }

    public int openMainMenu() {
        return promptMenuChoice(
                "--- Student Information Management System Console ---\n" +
                        "1. Student Management Menu\n" +
                        "2. Course Management Menu\n" +
                        "3. Enrollment Menu\n" +
                        "4. Close System\n" +
                        "Enter your selection to continue: ",
                4
        );
    }

    public int openStudentManagementMenu() {
        return promptMenuChoice(
                "--- Student Management Menu ---\n" +
                        "1. View All Student Records\n" +
                        "2. View One Student Record\n" +
                        "3. Add New Student Record\n" +
                        "4. Update Student Record\n" +
                        "5. Delete Student Record\n" +
                        "6. Return To Main Menu\n" +
                        "Enter your selection to continue: ",
                6
        );
    }

    public int openCourseManagementMenu() {
        return promptMenuChoice(
                "--- Course Management Menu ---\n" +
                        "1. View All Course Records\n" +
                        "2. View One Course Record\n" +
                        "3. Add New Course Record\n" +
                        "4. Update Course Record\n" +
                        "5. Delete Course Record\n" +
                        "6. Return To Main Menu\n" +
                        "Enter your selection to continue: ",
                6
        );
    }

    public int openEnrollmentMenu() {
        return promptMenuChoice(
                "--- Enrollment Management Menu ---\n" +
                        "1. Enroll Student\n" +
                        "2. Withdraw Student\n" +
                        "3. View Student's Enrolled Courses\n" +
                        "4. View Course's Enrolled Students\n" +
                        "5. Return To Main Menu\n" +
                        "Enter your selection to continue: ",
                5
        );
    }

    public <T extends BaseRecord> void printAllRecords(Collection<T> records) {
        if (records.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        for (BaseRecord record : records) {
            System.out.println(record.toString());
        }
    }

    public void printRecord(BaseRecord record) {
        System.out.println(record.toString());
    }

    public void pause() {
        System.out.print("Press enter to continue...");
        input.nextLine();
    }

    public int promptID(String prompt, Set<Integer> validIds) {
        int id;
        while (true) {
            System.out.print(prompt);
            id = input.nextInt();
            if (validateID(id, validIds)) {
                input.nextLine();
                return id;
            }
        }
    }

    public String promptString(String prompt) {
        String str;
        while (true) {
            System.out.print(prompt);
            str = input.nextLine();
            if (!str.isEmpty()) {
                return str;
            }
            System.out.println("Invalid String: Input cannot be empty.");
        }

    }

    public Date promptDate(String prompt) {
        String dateStr;
        while (true) {
            System.out.print(prompt);
            dateStr = input.nextLine();
            if (validateDate(dateStr)) {
                return new Date(dateStr);
            }
        }
    }

    public boolean validateDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            System.out.printf("Invalid Date '%s'.\n", dateStr);
            return false;
        }
    }

    public boolean validateID(int id, Set<Integer> validIds) {
        if (id < 0) {
            System.out.printf("Invalid ID '%d': ID cannot be negative.\n", id);
            return false;
        }

        if (!validIds.contains(id)) {
            System.out.printf("Invalid ID '%d': No matching ID found.\n", id);
            return false;
        }
        return true;

    }
}
