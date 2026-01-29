import java.util.HashSet;


public class Student {
    private int id;
    private String name;
    private Date dob;
    private HashSet<Integer> enrolledCourses;

    public Student(int id, String name, Date dob){
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.enrolledCourses = new HashSet<>();
    }

    public Student(int id, String name, Date dob, HashSet<Integer> enrolledCourses){
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.enrolledCourses = enrolledCourses;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public HashSet<Integer> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enroll(Course course){
        int courseId = course.getId();
        if(enrolledCourses.contains(courseId)){
            System.out.println("Student is already enrolled in this class.");
        }else{
            enrolledCourses.add(courseId);
            System.out.println("Student successfully enrolled.");
        }
    }

    public void withdraw(int courseId){
        if(!enrolledCourses.contains(courseId)){
            System.out.println("Student is not enrolled in this class.");
        }else{
            enrolledCourses.remove(courseId);
            System.out.println("Student successfully withdrawn.");
        }
    }
}
