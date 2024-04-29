import java.util.*;
import java.util.ArrayList;

public class Student {

    private static final int MAX_NUM_REGISTERED_COURSES = 2;
    private static final int MAX_REGISTERED_CREDIT_HRS = 4;
    private int maxNumRegisteredCourses;
    private int totalRegisteredCreditHrs;
    private String name;
    private static int count;
    private int id;
    private String standing;
    private ArrayList<Course> registeredCourses = new ArrayList<>();
    private int totalCredits = 0;




    //private ArrayList<int> allStudents = new ArrayList<>();



    public Student(String name, int id, String standing) {
        this.maxNumRegisteredCourses = MAX_NUM_REGISTERED_COURSES;
        this.totalRegisteredCreditHrs = MAX_REGISTERED_CREDIT_HRS;
        this.name = name;
        this.id = id;
        this.standing = standing;
        count++;
        count=id;
        //String newID;
        //allStudents.add(id);
        if (standing=="Freshmen") {
            this.standing=standing;
        }
        else if (standing=="Sophomore") {
            this.standing=standing;
        }
        else if (standing=="Junior") {
            this.standing=standing;
        }
        else if (standing=="Senior") {
            this.standing=standing;
            this.maxNumRegisteredCourses += 1;
            this.totalRegisteredCreditHrs += (totalRegisteredCreditHrs * 0.2);
        }
        else {
            System.out.println("Invalid");
        }

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStanding() {
        return standing;
    }

    public void setStanding(String standing) {
        this.standing = standing;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    //public ArrayList<allStudents> getAllStudentList(){
     //   return allStudents;
    //}

    public void setRegisteredCourses(List<Course> registeredCourses) {
        this.registeredCourses = (ArrayList<Course>) registeredCourses;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }
    public String toString(){
         return "Name: " + name +  "ID: " + id + "Standing: " + standing;
    }

    public void setTotalRegisteredCreditHrs(final int totalRegisteredCreditHrs){
        this.totalRegisteredCreditHrs=totalRegisteredCreditHrs;
    }

    public void setMaxNumRegisteredCourses(final int maxNumRegisteredCourses){
        this.maxNumRegisteredCourses=maxNumRegisteredCourses;
    }

    public int getMaxNumRegisteredCourses(){
        return maxNumRegisteredCourses;
    }

    public int getTotalRegisteredCreditHrs(){
        return totalRegisteredCreditHrs;
    }


    public void registerCourse(Course course) {
        if (isRegistered(course)) {
            System.out.println("Already registered for this course.");
            return;
        }

        if (exceedsMaxCredits(course.getCreditHrs())) {
            System.out.println("Cannot register for this course as it exceeds the maximum credit limit.");
            return;
        }

        registeredCourses.add(course);
        totalCredits += course.getCreditHrs();
    }

    public void unregisterCourse(Course course) {
        if (!isRegistered(course)) {
            System.out.println("Cannot unregister from a course that you are not registered for.");
            return;
        }

        registeredCourses.remove(course);
        totalCredits -= course.getCreditHrs();
    }

    public boolean isRegistered(Course course) {
        return registeredCourses.contains(course);
    }

     public boolean exceedsMaxCredits(int credits) {
        return (totalCredits + credits) > totalRegisteredCreditHrs;
    }

    public String getYear() {
         return standing;
    }
    public int getTotalRegisteredHours() {
    return totalCredits;
}

}
