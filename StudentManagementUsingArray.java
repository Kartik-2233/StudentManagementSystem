package studentManagementSystem;

import java.util.*;

public class StudentManagementUsingArray {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Enter the number of student you want to add in your student");
			int numberOfStudents = sc.nextInt();
			sc.nextLine();
			String[] name = new String[numberOfStudents];
			short[] id = new short[numberOfStudents];
			String[] course = new String[numberOfStudents];

			for (int i = 0; i < numberOfStudents; i++) {

				id[i] = (short) (i + 1);

				System.out.println("Enter the Name of  Student " + (i + 1));
				name[i] = sc.nextLine();
				System.out.println("Enter the course of  Student " + (i + 1));
				course[i] = sc.nextLine();

			}
			
			for (int i = 0; i < numberOfStudents; i++) {
				System.out.println("ID= " + id[i] + " :Name => " + name[i] + " :course => " + course[i]);
			}
		} catch (NumberFormatException e) {
			System.out.println(" Invalid number format. Please enter a valid integer.");
		} catch (InputMismatchException e) {
			System.out.println(" Input mismatch. Please enter correct data types.");
		} catch (Exception e) {
			System.out.println(" Unexpected error: " + e.getMessage());
		} finally {
			sc.close();
			System.out.println("Program ended.");
		}

	}
}
