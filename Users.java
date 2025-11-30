package studentManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class Users {
    static Connection con = Connection1.getConnection();
    static Scanner sc = new Scanner(System.in);

    public static void register() {
        System.out.println("\n--- Register User ---");
        sc.nextLine(); // Clear buffer
        System.out.print("Username: ");
        String username = sc.nextLine();
        
        System.out.print("Password: ");
        String password = sc.nextLine();
        
        System.out.print("Role (Admin/Teacher): ");
        String role = sc.nextLine();

        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Registered Successfully!" : "⚠️ Registration Failed.");
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public static boolean login() {
        System.out.println("\n🔐 --- LOGIN ---");
        if(sc.hasNextLine()) sc.nextLine(); 
        
        System.out.print("Username: ");
        String username = sc.nextLine();
        
        System.out.print("Password: ");
        String password = sc.nextLine();

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("✅ Login Successful! Welcome, " + rs.getString("role"));
                return true;
            } else {
                System.out.println("❌ Invalid Username or Password.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("❌ Login Error: " + e.getMessage());
            return false;
        }
    }

    public static void viewUsers() {
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {
            System.out.println("\n--- User List ---");
            while(rs.next()) {
                System.out.println("User: " + rs.getString("username") + " | Role: " + rs.getString("role"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}