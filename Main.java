package studentManagementSystem;

import java.util.*;


public class Main { 
    static Scanner sc = new Scanner(System.in);
    
    void mainMenu() {
        int input = -1; // Initialize with a default value
        
        System.out.println("🎓 Welcome to Student Management System 🎓");

        do {
            try {
                System.out.println("\n========= MAIN MENU =========");
                System.out.println("1. Student Page");
                System.out.println("2. Teacher Page");
                System.out.println("3. Class Page");
                System.out.println("4. Exam Page");
                System.out.println("5. Fees Page");
                System.out.println("6. User Management (Login/Register)");
                System.out.println("7. Attendance Page");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                
                // Check if the input is actually an integer to prevent InputMismatchException
                if (sc.hasNextInt()) {
                    input = sc.nextInt();
                } else {
                    sc.next(); // Clear invalid input
                    System.out.println("❌ Invalid input. Please enter a number.");
                    continue; // Skip to next iteration
                }

                switch (input) {
                    // STUDENT MODULE
                    case 1 -> {
                        int studentInput = -1;
                        do {
                            System.out.println("\n------ Student Menu ------");
                            System.out.println("1. Create | 2. View | 3. Update | 4. Delete | 9. Back");
                            System.out.print("Choice: ");
                            if(sc.hasNextInt()) studentInput = sc.nextInt();
                            else { sc.next(); continue; }
                            
                            switch (studentInput) {
                                case 1 -> Student.create();
                                case 2 -> Student.read();
                                case 3 -> Student.update();
                                case 4 -> Student.delete();
                            }
                        } while (studentInput != 9);
                    }

                    // TEACHER MODULE (Assuming Class name is 'Teachers')
                    case 2 -> {
                        int teacherInput = -1;
                        do {
                            System.out.println("\n------ Teacher Menu ------");
                            System.out.println("1. Create | 2. View | 3. Update | 4. Delete | 9. Back");
                            System.out.print("Choice: ");
                            if(sc.hasNextInt()) teacherInput = sc.nextInt(); 
                            else { sc.next(); continue; }

                            switch (teacherInput) {
                                case 1 -> Teachers.create(); // Capitalized Class Name
                                case 2 -> Teachers.read();
                                case 3 -> Teachers.update();
                                case 4 -> Teachers.delete();
                            }
                        } while (teacherInput != 9);
                    }

                    // PLACEHOLDERS
                    case 3 -> System.out.println("📘 Class module coming soon...");
                    case 4 -> System.out.println("🧾 Exam module coming soon...");
                    case 5 -> System.out.println("💰 Fees module coming soon...");

                    // USER MANAGEMENT
                    case 6 -> {
                        int userInput = -1;
                        do {
                            System.out.println("\n------ User Management ------");
                            System.out.println("1. Register New User");
                            System.out.println("2. Test Login");
                            System.out.println("3. View All Users (Admin)");
                            System.out.println("9. Back");
                            System.out.print("Choice: ");
                            if(sc.hasNextInt()) userInput = sc.nextInt();
                            else { sc.next(); continue; }

                            switch (userInput) {
                                case 1 -> Users.register(); // Capitalized Class Name
                                case 2 -> Users.login();
                                case 3 -> Users.viewUsers();
                            }
                        } while (userInput != 9);
                    }

                    // ATTENDANCE MODULE
                    case 7 -> {
                        int attInput = -1;
                        do {
                            System.out.println("\n------ Attendance Menu ------");
                            System.out.println("1. Mark | 2. View | 3. Update | 4. Delete | 9. Back");
                            System.out.print("Choice: ");
                            if(sc.hasNextInt()) attInput = sc.nextInt();
                            else { sc.next(); continue; }

                            switch (attInput) {
                                case 1 -> Attendance.create();
                                case 2 -> Attendance.read();
                                case 3 -> Attendance.update();
                                case 4 -> Attendance.delete();
                            }
                        } while (attInput != 9);
                    }

                    case 0 -> System.out.println("👋 Exiting program. Goodbye!");
                    default -> System.out.println("❌ Invalid option. Try again.");
                }

            } catch (Exception e) {
                System.out.println("⚠️ Program Error: " + e.getMessage());
                sc.nextLine(); // Clear scanner buffer on error
            }
        } while (input != 0);
    }

    public static void main(String[] args) {
        Authentication auth = new Authentication();
        Main menu = new Main();
        
        // LOGIN SYSTEM
        System.out.println("🔐 Login Required");
        System.out.print("Enter username: ");
        String name = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();
        
        boolean valid = auth.auth(name, password);
        
        if (valid) {
            System.out.println("✅ Login Successful!");
            menu.mainMenu();
        } else {
            System.out.println("❌ Incorrect ID or Password.");
        }

       
        sc.close();
    }
}