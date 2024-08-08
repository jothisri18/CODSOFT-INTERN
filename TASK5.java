import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    int enrolled;
    String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
        this.schedule = schedule;
    }

    public boolean isAvailable() {
        return enrolled < capacity;
    }

    public void enrollStudent() {
        if (isAvailable()) {
            enrolled++;
        }
    }

    public void dropStudent() {
        if (enrolled > 0) {
            enrolled--;
        }
    }

    @Override
    public String toString() {
        return code + ": " + title + " (" + enrolled + "/" + capacity + ") - " + schedule + "\n" + description;
    }
}

class Student {
    String id;
    String name;
    ArrayList<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (course.isAvailable() && !registeredCourses.contains(course)) {
            registeredCourses.add(course);
            course.enrollStudent();
            System.out.println("Registered for course: " + course.title);
        } else {
            System.out.println("Cannot register for course: " + course.title);
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            System.out.println("Dropped course: " + course.title);
        } else {
            System.out.println("You are not registered for course: " + course.title);
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name;
    }
}

class CourseRegistrationSystem {
    private HashMap<String, Course> courses;
    private HashMap<String, Student> students;
    private Scanner scanner;

    public CourseRegistrationSystem() {
        courses = new HashMap<>();
        students = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void addCourse(Course course) {
        courses.put(course.code, course);
    }

    public void addStudent(Student student) {
        students.put(student.id, student);
    }

    public void listCourses() {
        for (Course course : courses.values()) {
            System.out.println(course);
        }
    }

    public void registerStudent() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.registerCourse(course);
    }

    public void dropStudentCourse() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.dropCourse(course);
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\nCourse Registration System Menu:");
            System.out.println("1. List Courses");
            System.out.println("2. Register Student for Course");
            System.out.println("3. Drop Student from Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerStudent();
                    break;
                case 3:
                    dropStudentCourse();
                    break;
                case 4:
                    System.out.println("Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }
}

public class CourseRegistration {
    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        // Add some courses
        system.addCourse(new Course("CS101", "Introduction to Computer Science", "Basic concepts of computer science.", 30, "MWF 10-11AM"));
        system.addCourse(new Course("MATH101", "Calculus I", "Introduction to calculus.", 25, "TTh 9-10:30AM"));
        system.addCourse(new Course("ENG101", "English Literature", "Study of English literature.", 20, "MWF 1-2PM"));

        // Add some students
        system.addStudent(new Student("S001", "Alice"));
        system.addStudent(new Student("S002", "Bob"));

        // Display the menu
        system.displayMenu();
    }
}
