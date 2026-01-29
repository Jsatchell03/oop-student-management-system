package app;

import models.*;
import ui.ConsoleUI;
import database.*;

import java.util.HashMap;

import java.util.Scanner;

public class StudentManagementSystem {
    private HashMap<Integer, Student> students = new HashMap<Integer, Student>();
    private HashMap<Integer, Course> courses = new HashMap<Integer, Course>();
    private ConsoleUI ui = new ConsoleUI();

    public StudentManagementSystem() {

    }

    public void run() {
        students = StudentDatabase.load();
        courses = CourseDatabase.load();
        int choice;
        while (true) {
            choice = ui.openMainMenu();
            switch (choice) {
                case 1 -> manageStudents();
                case 2 -> manageCourses();
                case 3 -> manageEnrollment();
                case 4 -> {
                    return;
                }
            }
        }
    }

    private void manageStudents() {
        int choice;
        while (true) {
            choice = ui.openStudentManagementMenu();
            switch (choice) {
                case 1 -> {
                    ui.printAllRecords(students.values());
                    ui.pause();
                }
                case 2 -> {
                    ui.printRecord(students.get(ui.promptId("Enter student ID: ", students.keySet())));
                    ui.pause();
                }
                case 6 -> {
                    return;
                }
            }
        }

    }

    private void manageCourses() {
        int choice = ui.openCourseManagementMenu();
    }

    private void manageEnrollment() {
        int choice = ui.openEnrollmentMenu();
    }

}
