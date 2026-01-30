package app;

import models.*;
import ui.ConsoleUI;
import database.*;

import java.util.HashMap;

public class StudentManagementSystem {
    private HashMap<Integer, Student> students = new HashMap<Integer, Student>();
    private HashMap<Integer, Course> courses = new HashMap<Integer, Course>();
    private ConsoleUI ui = new ConsoleUI();
    private StudentDatabase studentDB = new StudentDatabase();
    private CourseDatabase courseDB = new CourseDatabase();

    public StudentManagementSystem() {

    }

    public void run() {
        students = studentDB.load();
        courses = courseDB.load();
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
                    ui.printRecord(students.get(ui.promptID("Enter student ID: ", students.keySet())));
                    ui.pause();
                }

                case 3 -> {
                    Student newStudent = new Student(studentDB.getNewId(), ui.promptString("Enter student name: "), ui.promptDate("Enter student DOB (MM-DD-YYYY): "));
                    students.put(newStudent.getId(), newStudent);
                    studentDB.save(students);
                    ui.pause();
                }

                case 4 -> {
                    int updateID = ui.promptID("Enter student ID: ", students.keySet());
                    ui.printRecord(students.get(updateID));
                    Student updatedStudent = new Student(updateID, ui.promptString("Enter updated student name: "), ui.promptDate("Enter updated student DOB (MM-DD-YYYY): "));
                    students.put(updatedStudent.getId(), updatedStudent);
                    studentDB.save(students);
                    ui.pause();
                }

                case 5 -> {
                    int deleteID = ui.promptID("Enter student ID: ", students.keySet());
                    for (int courseID : students.get(deleteID).getEnrolledCourses()) {
                        courses.get(courseID).withdraw(deleteID);
                    }
                    students.remove(deleteID);
                    studentDB.save(students);
                    courseDB.save(courses);
                    ui.pause();
                }

                case 6 -> {
                    return;
                }
            }
        }
    }

    private void manageCourses() {
        int choice;
        while (true) {
            choice = ui.openCourseManagementMenu();
            switch (choice) {
                case 1 -> {
                    ui.printAllRecords(courses.values());
                    ui.pause();
                }

                case 2 -> {
                    ui.printRecord(courses.get(ui.promptID("Enter course ID: ", courses.keySet())));
                    ui.pause();
                }

                case 3 -> {
                    Course newCourse = new Course(courseDB.getNewId(), ui.promptString("Enter course title: "));
                    courses.put(newCourse.getId(), newCourse);
                    courseDB.save(courses);
                    ui.pause();
                }

                case 4 -> {
                    int updateID = ui.promptID("Enter course ID: ", courses.keySet());
                    ui.printRecord(courses.get(updateID));
                    Course updatedCourse = new Course(updateID, ui.promptString("Enter updated course title: "));
                    courses.put(updatedCourse.getId(), updatedCourse);
                    courseDB.save(courses);
                    ui.pause();
                }

                case 5 -> {
                    int deleteID = ui.promptID("Enter course ID: ", courses.keySet());
                    for (int studentID : courses.get(deleteID).getEnrolledStudents()) {
                        students.get(studentID).withdraw(deleteID);
                    }
                    courses.remove(deleteID);
                    courseDB.save(courses);
                    studentDB.save(students);
                    ui.pause();
                }

                case 6 -> {
                    return;
                }
            }
        }
    }

    private void manageEnrollment() {
        int choice;
        while (true) {
            choice = ui.openEnrollmentMenu();
            switch (choice) {
                case 1 -> {
                    int studentID = ui.promptID("Enter student ID: ", students.keySet());
                    int courseID = ui.promptID("Enter course ID: ", courses.keySet());
                    courses.get(courseID).enrollStudent(studentID);
                    students.get(studentID).enroll(courseID);
                    courseDB.save(courses);
                    studentDB.save(students);
                    ui.pause();
                }

                case 2 -> {
                    int studentID = ui.promptID("Enter student ID: ", students.keySet());
                    int courseID = ui.promptID("Enter course ID: ", students.get(studentID).getEnrolledCourses());
                    courses.get(courseID).withdraw(studentID);
                    students.get(studentID).withdraw(courseID);
                    courseDB.save(courses);
                    studentDB.save(students);
                    ui.pause();
                }

                case 3 -> {
                    for (int courseID : students.get(ui.promptID("Enter student ID: ", students.keySet())).getEnrolledCourses()) {
                        ui.printRecord(courses.get(courseID));
                    }
                    ui.pause();
                }

                case 4 -> {
                    for (int studentID : courses.get(ui.promptID("Enter course ID: ", courses.keySet())).getEnrolledStudents()) {
                        ui.printRecord(students.get(studentID));
                    }
                    ui.pause();
                }

                case 5 -> {
                    return;
                }
            }
        }
    }

}
