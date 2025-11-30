package studentManagementSystem;

import java.sql.*;
import java.util.*;

public class Teachers {

    static Connection con = Connection1.getConnection();
    static Scanner sc = new Scanner(System.in);

    public static void create() {
        String sql = "INSERT INTO teachers(name, email, phone) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            sc.nextLine(); // Clear buffer
            System.out.print("Enter Teacher Name: ");
            ps.setString(1, sc.nextLine());

            System.out.print("Enter Email: ");
            ps.setString(2, sc.nextLine());

            System.out.print("Enter Phone: ");
            ps.setLong(3, sc.nextLong());

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Teacher added." : "⚠️ Failed.");
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public static void read() {
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM teachers")) {

            System.out.printf("%-5s %-20s %-25s %-15s%n", "ID", "Name", "Email", "Phone");
            System.out.println("-------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-5d %-20s %-25s %-15d%n",
                        rs.getInt("teacher_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getLong("phone"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public static void update() {
        System.out.print("Enter Teacher ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); 

        String sql = "UPDATE teachers SET name=?, email=?, phone=? WHERE teacher_id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            System.out.print("New Name: "); ps.setString(1, sc.nextLine());
            System.out.print("New Email: "); ps.setString(2, sc.nextLine());
            System.out.print("New Phone: "); ps.setLong(3, sc.nextLong());
            ps.setInt(4, id);

            System.out.println(ps.executeUpdate() > 0 ? "✅ Updated." : "⚠️ Not found.");
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public static void delete() {
        System.out.print("Enter Teacher ID to delete: ");
        int id = sc.nextInt();

        try (PreparedStatement ps = con.prepareStatement("DELETE FROM teachers WHERE teacher_id=?")) {
            ps.setInt(1, id);
            System.out.println(ps.executeUpdate() > 0 ? "✅ Deleted." : "⚠️ Not found.");
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}