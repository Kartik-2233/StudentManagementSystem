package studentManagementSystem;

import java.io.*;
import java.util.*;

class Student1 {
    short id;
    String name;
    String course;

    Student1(short id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public String toString() {
        return "ID = " + id + " | Name => " + name + " | Course => " + course;
    }
}

public class StudentManagementUsingArraylist {
    static ArrayList<Student1> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n📚 Student Management Menu:");
            System.out.println("1. Add Students");
            System.out.println("2. View All Students");
            System.out.println("3. Save to File");
            System.out.println("4. Read from File");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> addStudents();
                case 2 -> viewStudents();
                case 3 -> saveToFile();
                case 4 -> readFromFile();
                case 5 -> System.out.println("👋 Exiting... Goodbye!");
                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    static void addStudents() {
        System.out.print("Enter number of students to add: ");
        int count = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < count; i++) {
            short id = (short) (students.size() + 1);
            System.out.println("Enter name for student " + id + ":");
            String name = sc.nextLine().trim();
            System.out.println("Enter course for student " + id + ":");
            String course = sc.nextLine().trim();

            if (name.isEmpty() || course.isEmpty()) {
                System.out.println("❌ Name and course cannot be empty. Try again.");
                i--;
                continue;
            }

            students.add(new Student1(id, name, course));
        }
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("⚠️ No students added yet.");
        } else {
            System.out.println("\n📋 Current Student List:");
            for (Student1 s : students) {
                System.out.println(s);
            }
        }
    }

    static void saveToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("Student Records:\n");
            for (Student1 s : students) {
                writer.write(s.toString() + "\n");
            }
            System.out.println("✅ Student data saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("⚠️ Error saving to file:");
            e.printStackTrace();
        }
    }

    static void readFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("⚠️ File not found. Save data first.");
            return;
        }

        System.out.println("\n📖 Reading from " + FILE_NAME + ":");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error reading file:");
            e.printStackTrace();
        }
    }
}