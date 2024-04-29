import java.util.*;
import java.util.ArrayList;

public class Course {
    private String name;
    private String id;
    private int creditHrs;
    private int classSize=0;
    private int maxClassSize;
    private ArrayList<Student> studentsInCourse = new ArrayList<>();
    private ArrayList<String> allCourses = new ArrayList<>();


    public Course(String name, String id, int creditHrs, int maxClassSize) {
        this.name = name;
        this.id = id;
        this.creditHrs = creditHrs;
        this.maxClassSize = maxClassSize;
        classSize++;
        allCourses.add(id);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCreditHrs() {
        return creditHrs;
    }
    public int getClassSize(){
        return classSize;
    }

    public void setClassSize(int classSize){
        this.classSize=classSize;
    }

    public void setCreditHrs(int creditHrs) {
        this.creditHrs = creditHrs;
    }

    public int getMaxClassSize() {
        return maxClassSize;
    }

    public void setMaxClassSize(int maxClassSize) {
        this.maxClassSize = maxClassSize;
    }

    public List<Student> getStudents() {
        return studentsInCourse;
    }
    public List<String> getAllCoursesList(){
        return allCourses;
    }

    public void setStudents(List<Student> students) {
        this.studentsInCourse = (ArrayList<Student>) students;
    }


    public void addStudent(Student student) {
        studentsInCourse.add(student);
    }

    public void removeStudent(Student student) {
        studentsInCourse.remove(student);
    }

    public boolean isFull() {
    return classSize >= maxClassSize;
}

    public boolean isRegistered(Student student) {
        return studentsInCourse.contains(student);
    }

    public int getCreditHours() {
        return this.creditHrs;
    }

    public String toString(){
        return "Course Name: " + name +  "Course ID: " + id + "Credit: " + creditHrs + " Maximum Size: " + maxClassSize ;
    }
}
