package studentManagementSystem;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Attendance {
    static Connection con = Connection1.getConnection();
    static Scanner sc = new Scanner(System.in);

    public static void create() {
        String sql = "INSERT INTO attendance(student_id, class_id, date, status) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            System.out.print("Enter Student ID: ");
            long studentId = sc.nextLong();
            ps.setLong(1, studentId);

            System.out.print("Enter Class ID: ");
            long classId = sc.nextLong();
            ps.setLong(2, classId);

            LocalDate today = LocalDate.now();
            ps.setDate(3, Date.valueOf(today));

            System.out.println("Enter Attendance: 1 -> Absent | 2 -> Present");
            int choice = sc.nextInt();
            String status = (choice == 2) ? "Present" : "Absent";
            ps.setString(4, status);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Attendance marked!" : "⚠️ Failed.");

        } catch (SQLException e) {
            System.out.println("❌ Database Error: " + e.getMessage());
        }
    }

    public static void read() {
        String query = "SELECT * FROM attendance";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n===== ATTENDANCE RECORDS =====");
            System.out.printf("%-10s %-12s %-12s %-12s %-10s%n", "ID", "Std_ID", "Cls_ID", "Date", "Status");
            System.out.println("------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-10d %-12d %-12d %-12s %-10s%n",
                        rs.getLong("attendance_id"),
                        rs.getLong("student_id"),
                        rs.getLong("class_id"),
                        rs.getDate("date"),
                        rs.getString("status"));
            }
        } catch (SQLException se) {
            System.out.println("❌ SQL Error: " + se.getMessage());
        }
    }

    public static void update() {
        System.out.print("Enter Attendance ID to update: ");
        long id = sc.nextLong();
        System.out.println("New Status: 1 -> Absent | 2 -> Present");
        int choice = sc.nextInt();
        String newStatus = (choice == 2) ? "Present" : "Absent";

        String query = "UPDATE attendance SET status = ? WHERE attendance_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, newStatus);
            ps.setLong(2, id);
            System.out.println(ps.executeUpdate() > 0 ? "✅ Updated!" : "⚠️ ID not found.");
        } catch (SQLException e) {
            System.out.println("❌ SQL Error: " + e.getMessage());
        }
    }

    public static void delete() {
        System.out.print("Enter Attendance ID to delete: ");
        long id = sc.nextLong();
        String query = "DELETE FROM attendance WHERE attendance_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, id);
            System.out.println(ps.executeUpdate() > 0 ? "✅ Deleted." : "⚠️ Not found.");
        } catch (SQLException e) {
            System.out.println("❌ SQL Error: " + e.getMessage());
        }
    }
}