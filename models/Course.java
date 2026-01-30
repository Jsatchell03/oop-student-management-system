package models;

import java.util.HashSet;

public class Course extends BaseRecord {
    private int id;
    private String title;
    private HashSet<Integer> enrolledStudents;

    public Course(int id, String title, HashSet<Integer> enrolledStudents) {
        this.id = id;
        this.title = title;
        this.enrolledStudents = enrolledStudents;
    }

    public Course(int id, String title) {
        this.id = id;
        this.title = title;
        this.enrolledStudents = new HashSet<Integer>();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashSet<Integer> getEnrolledStudents() {
        return enrolledStudents;
    }

    @Override
    public String toString() {
        return String.format("%d | %s", id, title);
    }

    public void enrollStudent(int studentId) {
        if (enrolledStudents.contains(studentId)) {
            System.out.println("Student is already enrolled in this class.");
        } else {
            enrolledStudents.add(studentId);
        }
    }

    public void withdraw(int studentId) {
        if (!enrolledStudents.contains(studentId)) {
            System.out.println("Student is not enrolled in this class.");
        } else {
            enrolledStudents.remove(studentId);
        }
    }
}
