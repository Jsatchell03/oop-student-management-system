package app;

import models.Course;
import models.Date;
import models.Student;

import java.util.HashMap;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentManagementSystem {
    private HashMap<Integer, Student> students = new HashMap<Integer, Student>();
    private HashMap<Integer, Course> courses = new HashMap<Integer, Course>();
    final private String STUDENT_DATA_FILE_PATH = "data/students.txt";
    final private String COURSE_DATA_FILE_PATH = "data/courses.txt";
    private Scanner input = new Scanner(System.in);

    public StudentManagementSystem(){
        loadData();
    }

    private void loadData(){
        // Load students
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_DATA_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                int id = Integer.parseInt(record[0]);
                String name = record[1];
                Date dob = Date.parseString(record[2]);
                HashSet<Integer> courses = Arrays.stream(record[3].split(";"))
                                            .map(Integer::parseInt)
                                            .collect(Collectors.toCollection(HashSet::new));
                students.put(id, new Student(id, name, dob, courses));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        // Load courses
        try (BufferedReader reader = new BufferedReader(new FileReader(COURSE_DATA_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                int id = Integer.parseInt(record[0]);
                String title = record[1];
                HashSet<Integer> students = Arrays.stream(record[2].split(";"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toCollection(HashSet::new));
                courses.put(id, new Course(id, title, students));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private void updateStudentData(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(STUDENT_DATA_FILE_PATH))) {
            for(Student student : students.values()){
                String courses = student.getEnrolledCourses().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(";"));
                bw.write(String.format("%d,%s,%s,%s\n",
                        student.getId(), student.getName(), student.getDob().toString(), courses));
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    private void updateCourseData(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(COURSE_DATA_FILE_PATH))) {
            for(Course course : courses.values()){
                String students = course.getEnrolledStudents().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(";"));
                bw.write(String.format("%d,%s,%s\n",
                        course.getId(), course.getTitle(), students));
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public int promptMenuChoice(String prompt, int max){
        System.out.print(prompt);
        int choice = input.nextInt();
        while(choice < 1 || choice > max){
            System.out.printf("Invalid Selection: Enter a number from 1 - %d.\n", max);
            System.out.print("Enter your selection to continue: ");
            choice = input.nextInt();
        }
        input.nextLine();
        return choice;
    }

    public void openMainMenu(){
        int choice = promptMenuChoice(
                "--- models.Student Information Management System Console ---\n" +
                        "1. models.Student Management Menu\n" +
                        "2. models.Course Management Menu\n" +
                        "3. Enrollment Menu\n" +
                        "4. Close System\n" +
                        "Enter your selection to continue: ",
                4
        );

        switch (choice){
            case 1 -> openStudentManagementMenu();
            case 2 -> openCourseManagementMenu();
            case 3 -> openEnrollmentMenu();
        }
    }

    public void openStudentManagementMenu(){
        int choice = promptMenuChoice(
                "--- models.Student Management Menu ---\n" +
                        "1. View All models.Student Records\n" +
                        "2. View One models.Student Record\n" +
                        "3. Add New models.Student Record\n" +
                        "4. Update models.Student Record\n" +
                        "5. Delete models.Student Record\n" +
                        "6. Return To Main Menu\n" +
                        "Enter your selection to continue: ",
                6
        );
    }

    public void openCourseManagementMenu(){
        int choice = promptMenuChoice(
                "--- models.Course Management Menu ---\n" +
                        "1. View All models.Course Records\n" +
                        "2. View One models.Course Record\n" +
                        "3. Add New models.Course Record\n" +
                        "4. Update models.Course Record\n" +
                        "5. Delete models.Course Record\n" +
                        "6. Return To Main Menu\n" +
                        "Enter your selection to continue: ",
                6
        );
    }

    public void openEnrollmentMenu(){
        int choice = promptMenuChoice(
                "--- Enrollment Management Menu ---\n" +
                        "1. Enroll models.Student\n" +
                        "2. Withdraw models.Student\n" +
                        "3. View models.Student Enrolled Courses\n" +
                        "4. View models.Course Enrolled Students\n" +
                        "5. Return To Main Menu\n" +
                        "Enter your selection to continue: ",
                5
        );
    }

    public void viewStudentRecords(){

    }

    public void viewStudentRecord(int id){

    }

}
