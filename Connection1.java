package studentManagementSystem;

import java.sql.*;

public class Connection1 {
    private static Connection con = null;
    
    // Database Credentials
    private static final String URL = "jdbc:mysql://localhost:3306/school_management";
    private static final String USER = "root";
    private static final String PASS = "223362"; 

    public static Connection getConnection() {
        try {
            // Re-establish connection if it is null or closed
            if (con == null || con.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(URL, USER, PASS);
            }
        } catch (Exception e) {
            System.out.println("❌ Connection Error: " + e.getMessage());
        }
        return con;
    }

    public static void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("🔒 Connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}