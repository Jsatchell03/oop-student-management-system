package database;
import models.Date;
import models.Student;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentDatabase {
    private final static String FILE_PATH = "data/students.txt";

    public static HashMap<Integer, Student> load() {
        HashMap<Integer, Student> students = new HashMap<Integer, Student>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
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
        return students;
    }

    public static void save(Map<Integer, Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
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
}
