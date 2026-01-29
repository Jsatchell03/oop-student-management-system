package database;

import models.Course;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public class CourseDatabase {
    public static final String FILE_PATH = "data/courses.txt";

    public static HashMap<Integer, Course> load() {
        HashMap<Integer, Course> courses = new HashMap<Integer, Course>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
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
        return courses;
    }

    public static void save(HashMap<Integer, Course> courses) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Course course : courses.values()) {
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


}
