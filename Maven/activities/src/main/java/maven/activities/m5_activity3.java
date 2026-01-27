package maven.activities;

import java.sql.*;
import java.util.Scanner;

public class m5_activity3 {
	
	//DATABASE CONNECTION
    public static Connection getConnection() {
        try {
            String url = "jdbc:postgresql://localhost:5432/training_db";
            String user = "jiasanchez";
            String pass = "postgres";
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void addStudent(String name, int age, String email) {
        String sql = "INSERT INTO students (name, age, email) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, email);

            stmt.executeUpdate();
            System.out.println("Student added successfully!");

        } catch (Exception e) {
            System.out.println("Error adding student.");
            e.printStackTrace();
        }
    }
    
    public static void addCourse(String courseName, String grade, int studentId) {
        String sql = "INSERT INTO courses (course_name, grade, student_id) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, courseName);
            stmt.setString(2, grade);
            stmt.setInt(3, studentId);

            stmt.executeUpdate();
            System.out.println("Course added successfully!");

        } catch (Exception e) {
            System.out.println("Error adding course.");
            e.printStackTrace();
        }
    }
    
    public static void displayStudents() {
        String sql = "SELECT * FROM students";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("ID | Name | Age | Email");
            System.out.println("-----------------------");
            while (rs.next()) {
                System.out.println(
                     + rs.getInt("id") +
                    " | " + rs.getString("name") +
                    " | " + rs.getInt("age") +
                    " | " + rs.getString("email")
                );
            }

        } catch (Exception e) {
            System.out.println("Error reading students.");
            e.printStackTrace();
        }
    }
    
    public static void displayCourses() {
        String sql = 
            "SELECT c.id, c.course_name, c.grade, s.name AS student_name " +
            "FROM courses c " +
            "JOIN students s ON c.student_id = s.id";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("ID | Course Name | Grade | Student");
            System.out.println("----------------------------------");
            while (rs.next()) {
                System.out.println(
                     rs.getInt("id") +
                    " | " + rs.getString("course_name") +
                    " | " + rs.getString("grade") +
                    " |  " + rs.getString("student_name")
                );
            }

        } catch (Exception e) {
            System.out.println("Error reading courses.");
            e.printStackTrace();
        }
    }		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== STUDENT COURSE MANAGEMENT =====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Display All Students");
            System.out.println("4. Display All Courses");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {

                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    addStudent(name, age, email);
                    break;

                case 2:
                    System.out.print("Enter course name: ");
                    String course = sc.nextLine();

                    System.out.print("Enter grade: ");
                    String grade = sc.nextLine();

                    System.out.print("Enter student ID: ");
                    int studentId = sc.nextInt();

                    addCourse(course, grade, studentId);
                    break;

                case 3:
                    displayStudents();
                    break;

                case 4:
                    displayCourses();
                    break;

                case 5:
                    System.out.println("Program Exiting");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
}
	}


