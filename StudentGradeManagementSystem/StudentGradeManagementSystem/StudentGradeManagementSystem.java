import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeManagementSystem {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        int[] marks = new int[3];
        for (int i = 0; i < 3; i++) {
            while (true) {
                System.out.print("Enter mark for Subject " + (i + 1) + " (0-100): ");
                try {
                    marks[i] = Integer.parseInt(sc.nextLine());
                    if (marks[i] >= 0 && marks[i] <= 100) {
                        break;
                    } else {
                        System.out.println("Marks must be between 0 and 100.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }
        }

        students.add(new Student(name, marks));
        System.out.println("Student added successfully!");
    }

    public static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }
        System.out.println("\\n========== ALL STUDENTS ==========");
        for (Student s : students) {
            s.displayStudent();
        }
    }

    public static void searchStudent() {
        System.out.print("Enter student name to search: ");
        String searchName = sc.nextLine().trim();
        boolean found = false;

        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(searchName)) {
                s.displayStudent();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public static void summaryReport() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        double totalAverage = 0;
        int highestScore = -1;
        int lowestScore = 101;
        String topStudent = "";
        String lowestStudent = "";

        System.out.println("\\n========== SUMMARY REPORT ==========");
        for (Student s : students) {
            totalAverage += s.getAverage();
            for (int mark : s.getMarks()) {
                if (mark > highestScore) {
                    highestScore = mark;
                    topStudent = s.getName();
                }
                if (mark < lowestScore) {
                    lowestScore = mark;
                    lowestStudent = s.getName();
                }
            }
        }

        double classAverage = totalAverage / students.size();
        System.out.println("Total Students      : " + students.size());
        System.out.printf("Class Average       : %.2f%n", classAverage);
        System.out.println("Highest Score       : " + highestScore + " (" + topStudent + ")");
        System.out.println("Lowest Score        : " + lowestScore + " (" + lowestStudent + ")");
        System.out.println("\\n------ Student-wise Report ------");
        System.out.printf("%-20s %-10s %-10s%n", "Name", "Average", "Grade");

        for (Student s : students) {
            System.out.printf("%-20s %-10.2f %-10s%n",
                    s.getName(), s.getAverage(), s.getGrade());
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\\n===== STUDENT GRADE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Summary Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Enter a number from 1 to 5.");
                continue;
            }

            switch (choice) {
                case 1: addStudent(); break;
                case 2: displayAllStudents(); break;
                case 3: searchStudent(); break;
                case 4: summaryReport(); break;
                case 5:
                    System.out.println("Thank you for using the system!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
