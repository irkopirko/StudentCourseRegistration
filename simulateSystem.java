import java.util.List;
import java.util.Scanner;

public class SimulateSystem {
    public static void main(String[] args) {
        StudentCourseRegistration system = new StudentCourseRegistration();
        Scanner scanner = new Scanner(System.in);
           system.info();

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Please select an option:");
            System.out.println("1- List all Students in the system");
            System.out.println("2- List all Courses in the system");
            System.out.println("3- Register a student to a course");
            System.out.println("4- Try register a student to a course");
            System.out.println("5- Unregister a student from a course");
            System.out.println("6- Exit");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    for (Student student : system.getStudents()) {
                        String studentName = student.getName();
                        int studentId = student.getId();
                        String studentStanding = student.getStanding();

                        System.out.println(studentName + " " + studentId + " " + studentStanding);
                    }
                    break;
                case 2:
                    for (Course course : system.getCourses()) {
                        String courseName = course.getName();
                        String courseId = course.getId();
                        int creditHours = course.getCreditHrs();
                        int maxClassSize = course.getMaxClassSize();

                        System.out.println(courseName + " " + courseId + " " + creditHours + " " + maxClassSize);
                    }
                    break;
                 case 3:
                    System.out.println("Enter student id and course id:");
                    int studentId = scanner.nextInt();
                    String courseId = scanner.next();
                    String registrationResult = system.addRegistration(studentId, courseId);
                    System.out.println(registrationResult);
                    break;
                case 4:
                    System.out.println("Enter student id and course id:");
                    studentId = scanner.nextInt();
                    courseId = scanner.next();
                    String registerOrWaitResult = system.registerOrWait(studentId, courseId);
                    System.out.println(registerOrWaitResult);
                    break;
                case 5:
                    System.out.println("Enter student id and course id:");
                    studentId = scanner.nextInt();
                    courseId = scanner.next();
                    system.unregister(studentId, courseId);
                    System.out.println("Unregistered student " + studentId + " from course " + courseId);
                    break;
                case 6:
                    for (Student student : system.getStudents()) {
                        int id = student.getId();
                        List<Course> registeredCourses = student.getRegisteredCourses();
                        int registeredCourseSize = registeredCourses.size();
                        int totalCreditHours = 0;
                        for (Course course : registeredCourses) {
                            totalCreditHours += course.getCreditHrs();
                        }
                        System.out.println(id + " : " + registeredCourseSize + " " + totalCreditHours);
                    }

                    for (Course course : system.getCourses()) {
                        String id = course.getId();
                        List<Registration> waitingList = system.getWaitingList();
                        int waitingSize = 0;
                        for (Registration registration : waitingList) {
                            if (registration.getCourse().getId().equals(id)) {
                                waitingSize++;
                            }
                        }
                        int studentSize = course.getStudents().size();
                        System.out.println(id + " : " + studentSize + " " + waitingSize);
                    }

                    for (Registration registration : system.getWaitingList()) {
                        String studentName = registration.getStudent().getName();
                        String courseName = registration.getCourse().getName();
                        System.out.println(studentName + " " + courseName);
                    }
                    isRunning = false;
                    break;
            }
        }
    }
}
