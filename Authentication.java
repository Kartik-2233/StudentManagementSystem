package studentManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class Authentication {

	static Connection con = Connection1.getConnection();
	static Scanner sc = new Scanner(System.in);

	public boolean auth(String UName, String password) {
		boolean verified = false;

		String sql = ("Select * from users"); // write qquery
		try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next() == true) {
				String getName = rs.getString("username");
				String getPassword = rs.getString("password");

				if (UName.equals(getName) && password.equals(getPassword)) {
					verified = true;

				} else {
					verified = false;

				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return verified;

	}

	public static void main(String[] args) {
		Authentication ath = new Authentication();
		System.out.println("Enter username");
		String name = sc.next();
		System.out.println("Enter password");
		String password = sc.next();
		boolean valid = ath.auth(name, password);
		if (valid) {
			System.out.println("Login Done");
		} else {
			System.out.println("incorrect id and password");
		}
	}
}
