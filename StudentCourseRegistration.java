import java.util.ArrayList;

public class StudentCourseRegistration {
    private ArrayList<Registration> registrations;
    private ArrayList<Registration> waitingList;
    private ArrayList<Student> students;
    private ArrayList<Course> courses;

    public StudentCourseRegistration() {
        this.registrations = new ArrayList<>();
        this.waitingList = new ArrayList<>();
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        populateStudents();
        populateCourses();
    }

    private void populateStudents() {
        students.add(new Student("HasanHasan", 10, "Freshmen"));
        students.add(new Student("AhmetMehmet", 11, "Sophomore"));
        students.add(new Student("HasanHuseyin", 22, "Junior"));
        students.add(new Student("AyseFatma", 13, "Senior"));
        students.add(new Student("KazimKazim", 9, "Freshmen"));
        students.add(new Student("LaleCeren", 123, "Sophomore"));
        students.add(new Student("HaleJale", 666, "Junior"));
        students.add(new Student("ObladiOblada", 4004, "Senior"));
        students.add(new Student("SelenSeren", 42, "Freshmen"));
        students.add(new Student("MustafaKeser", 2459, "Sophomore"));
        students.add(new Student("SudeCansu", 7, "Junior"));
    }

    private void populateCourses() {
        courses.add(new Course("Programming", "cs0", 1, 2));
        courses.add(new Course("Mathematics", "ee2", 2, 2));
        courses.add(new Course("Software", "cs1", 1, 2));
        courses.add(new Course("Circuits", "ee3", 3, 3));
        courses.add(new Course("UnderwaterVoleyball", "uw1", 1, 3));
        courses.add(new Course("PrinciplesOfLife", "pol7", 4, 616));
        courses.add(new Course("CriticalThinking", "all", 42, 42));
    }
   public void info() {
        System.out.println("\n----- Students Information -----");
        for (Student student : students) {
            System.out.println("Name: " + student.getName() + ", ID: " + student.getId() + ", Standing: " + student.getYear());
        }

        System.out.println("\n----- Courses Information -----");
        for (Course course : courses) {
            System.out.println("Name: " + course.getName() + ", ID: " + course.getId() + ", Credit Hours: " + course.getCreditHrs() + ", Max Class Size: " + course.getMaxClassSize());
        }
    }


    public String addRegistration(int studentId, String courseId) {
        Student student = findStudentById(studentId);
        Course course = findCourseById(courseId);


        if (student == null) {
              return "UnknownStudentId";
        }
        if (course == null) {
              return "UnknownCourseId";
        }    


       int numRegisteredCourses = student.getRegisteredCourses().size();
       if (numRegisteredCourses >= student.getMaxNumRegisteredCourses()) {
            return "ExceedingMaxNumCourses";
       }

        int numStudentsInCourse = course.getStudents().size();
        if (numStudentsInCourse >= course.getMaxClassSize()) {
            return "ExceedingMaxNumStudents";
        }


        boolean alreadyRegistered = isAlreadyRegistered(student, course);
        if (alreadyRegistered) {
            return "ExistingRegistration";
        }

        Registration registration = new Registration(student, course);
        registrations.add(registration);

        ArrayList<Course> studentCourses = (ArrayList<Course>) student.getRegisteredCourses();
        studentCourses.add(course);

        ArrayList<Student> courseStudents = (ArrayList<Student>) course.getStudents();
        courseStudents.add(student);

        return "SuccessfullyRegistered";
    }
    public String unregister(int studentId, String courseId) {
    Student student = findStudentById(studentId);
    Course course = findCourseById(courseId);
    Registration registration = findRegistration(student, course);
    Registration waitingRegistration = null;

    if (student == null) {
        return "UnknownStudentId";
    }
    if (course == null) {
        return "UnknownCourseId";
    }
    if (registration == null){
        return "NotRegisteredToCourse";
    }

    registrations.remove(registration);
    student.getRegisteredCourses().remove(course);
    course.getStudents().remove(student);

    for (Registration r : waitingList) {
        if (r.getCourse().equals(course)) {
            waitingRegistration = r;
            break;
        }
    }

    if (waitingRegistration != null) {
        addRegistration(waitingRegistration.getStudent().getId(), courseId);
        waitingList.remove(waitingRegistration);
        return "SuccessfullyUnregisteredAndWaitingStudentRegistered";
    }

    return "SuccessfullyUnregistered";
}

public String registerOrWait(int studentId, String courseId) {
    String result = addRegistration(studentId, courseId);
    if (result.equals("ExceedingMaxNumStudents")) {
        Student student = findStudentById(studentId);
        Course course = findCourseById(courseId);
        Registration registration = new Registration(student, course);
        waitingList.add(registration);
        return "AddedToWaitingList";
    }
    return result;
}

     private Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    private Course findCourseById(String courseId) {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    private Registration findRegistration(Student student, Course course) {
        for (Registration registration : registrations) {
            if (registration.getStudent().equals(student) && registration.getCourse().equals(course)) {
                return registration;
            }
        }
        return null;
    }
    private boolean isAlreadyRegistered(Student student, Course course) {
        for (Registration registration : registrations) {
            boolean sameStudent = registration.getStudent().equals(student);
            boolean sameCourse = registration.getCourse().equals(course);

            if (sameStudent && sameCourse) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Registration> getRegistrations() {
        return registrations;
    }

    public ArrayList<Registration> getWaitingList() {
        return waitingList;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

}
