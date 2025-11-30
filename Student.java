package studentManagementSystem;

import java.sql.*;
import java.util.*;

public class Student {
    static Connection con = Connection1.getConnection();
    static Scanner sc = new Scanner(System.in);

    // CREATE
    public static void create() {
        String sql = "INSERT INTO students(name,email,phone,class_id,dob,address) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            sc.nextLine(); // Clear buffer
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();
            ps.setString(1, name);

            System.out.print("Enter Student Email ID: ");
            String email = sc.nextLine();
            ps.setString(2, email);

            System.out.print("Enter Phone Number: ");
            long phone = sc.nextLong();
            ps.setLong(3, phone);

            System.out.print("Enter Class ID: ");
            long class_id = sc.nextLong();
            ps.setLong(4, class_id);
            sc.nextLine(); // Clear buffer

            // SAFE DATE INPUT
            java.sql.Date dob = null;
            while (dob == null) {
                System.out.print("Enter DOB (YYYY-MM-DD): ");
                String dateInput = sc.nextLine();
                try {
                    dob = java.sql.Date.valueOf(dateInput);
                } catch (IllegalArgumentException e) {
                    System.out.println("⚠️ Invalid Date Format. Please use YYYY-MM-DD.");
                }
            }
            ps.setDate(5, dob);

            System.out.print("Enter Address: ");
            String address = sc.nextLine();
            ps.setString(6, address);

            int rows = ps.executeUpdate();
            System.out.println(rows + " record inserted successfully.");

        } catch (SQLException e) {
            System.out.println("❌ Insert failed: " + e.getMessage());
        }
    }

    // READ
    public static void read() {
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {

            System.out.println("------------------------------------------------------------");
            System.out.printf("%-5s %-20s %-25s %-12s %-12s%n", 
                              "ID", "Name", "Email", "Phone", "DOB");
            System.out.println("------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-5d %-20s %-25s %-12d %-12s%n",
                                  rs.getInt("student_id"),
                                  rs.getString("name"),
                                  rs.getString("email"),
                                  rs.getLong("phone"),
                                  rs.getDate("dob"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Read failed: " + e.getMessage());
        }
    }

    // UPDATE
    public static void update() {
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // Clear buffer

        // Note: For brevity, we are updating name only in this snippet. 
        // You can expand this to update all fields like Create().
        System.out.print("Enter new Name: ");
        String name = sc.nextLine();

        String sql = "UPDATE students SET name=? WHERE student_id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Record updated." : "⚠️ ID not found.");
        } catch (SQLException e) {
            System.out.println("❌ Update failed: " + e.getMessage());
        }
    }

    // DELETE
    public static void delete() {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM students WHERE student_id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Record deleted." : "⚠️ ID not found.");
        } catch (SQLException e) {
            System.out.println("❌ Delete failed: " + e.getMessage());
        }
    }
}