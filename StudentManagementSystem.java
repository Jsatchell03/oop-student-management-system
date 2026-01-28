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
                System.out.println(Arrays.toString(record));

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
                System.out.println(Arrays.toString(record));
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

    public void openMainMenu(){

    }


}
