package ProjectDB;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import ProjectDB.DBConnection;

public class StudentManagmentSystem{

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
    	StudentManagmentSystem obj = new StudentManagmentSystem();
        obj.SMS();
    }

    public void SMS() {
        while (true) {
            System.out.println("\nEnter your choice");
            System.out.println("1: Create table");
            System.out.println("2: Insert data");
            System.out.println("3: Delete data");
            System.out.println("4: Update data");
            System.out.println("5: Fetch by ID");
            System.out.println("6 View all Student Record ");
            System.out.println("7: Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createTable();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                	System.out.println("Enter student ID to delete:");
                    String sid = sc.next();
                    deleteStudent(sid);
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                	  System.out.println("Enter student ID to fetch:");
                      String sid1 = sc.next();
                    fetchStudentSingleDetail(sid1);
                    break;
                    
                case 6:
                	viewAllStudent();
                	break;
                case 7:
                    System.out.println("Thank you 😊");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public void viewAllStudent() {

        String qur = "SELECT * FROM mystud111";

        try (Connection conn = DBConnection.dbConnect();
             PreparedStatement ps = conn.prepareStatement(qur);
             ResultSet rs = ps.executeQuery()) {

            if (!rs.next()) {
                System.out.println("No student records found.");
            } else {
                System.out.println("\n---- All Student Records ----");

                do {
                    System.out.println("----------------------------");
                    System.out.println("ID   : " + rs.getString("stdId"));
                    System.out.println("Name : " + rs.getString("stdname"));
                    System.out.println("Email: " + rs.getString("stdmail"));
                    System.out.println("DOB  : " + rs.getString("stddob"));
                } while (rs.next());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

	// Create table
    public void createTable() {
        String qur = "CREATE TABLE IF NOT EXISTS mystud111 ("
                + "stdId VARCHAR(30) PRIMARY KEY,"
                + "stdname VARCHAR(50),"
                + "stdmail VARCHAR(50),"
                + "stddob VARCHAR(30))";

        try (Connection conn = DBConnection.dbConnect();
             PreparedStatement ps = conn.prepareStatement(qur)) {

            ps.execute();
            System.out.println("Table created successfully 😊");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add student
    public void addStudent() {
        String qur = "INSERT INTO mystud111 (stdId, stdname, stdmail, stddob) VALUES (?,?,?,?)";

        try (Connection conn = DBConnection.dbConnect();
             PreparedStatement psmt = conn.prepareStatement(qur)) {

            System.out.println("Enter student ID:");
            String sid = sc.next();

            sc.nextLine(); // Clear buffer

            System.out.println("Enter student name:");
            String sname = sc.nextLine();

            System.out.println("Enter student email:");
            String semail = sc.next();

            System.out.println("Enter student DOB:");
            String sdob = sc.next();

            psmt.setString(1, sid);
            psmt.setString(2, sname);
            psmt.setString(3, semail);
            psmt.setString(4, sdob);

            int count = psmt.executeUpdate();

            if (count > 0) {
                System.out.println("Data inserted successfully 😊");
            } else {
                System.out.println("Data not inserted");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete student
    public void deleteStudent(String sid) {
    	//viewAllStudent();
        String qur = "DELETE FROM mystud111 WHERE stdId=?";
        fetchStudentSingleDetail(sid);
        

        try (Connection conn = DBConnection.dbConnect();
             PreparedStatement psmt = conn.prepareStatement(qur)) {

            

            psmt.setString(1, sid);
            int rowsDeleted = psmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Student with ID " + sid + " deleted successfully!");
            } else {
                System.out.println("No student found with ID " + sid);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update student
    public void updateStudent() {
    	
        String qur = "UPDATE mystud111 SET stdname=?, stdmail=?, stddob=? WHERE stdId=?";

        try (Connection conn = DBConnection.dbConnect();
             PreparedStatement psmt = conn.prepareStatement(qur)) {

            System.out.println("Enter student ID to update:");
            String sid = sc.next();

            sc.nextLine(); // Clear buffer

            System.out.println("Enter new student name:");
            String sname = sc.nextLine();

            System.out.println("Enter new student email:");
            String semail = sc.next();

            System.out.println("Enter new student DOB:");
            String sdob = sc.next();

            psmt.setString(1, sname);
            psmt.setString(2, semail);
            psmt.setString(3, sdob);
            psmt.setString(4, sid);

            int count = psmt.executeUpdate();

            if (count > 0) {
                System.out.println("Data updated successfully 😊");
            } else {
                System.out.println("No student found with ID " + sid);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fetch single student details
    public void fetchStudentSingleDetail(String sid) {
        String qur = "SELECT * FROM mystud111 WHERE stdId=?";

        try (Connection conn = DBConnection.dbConnect();
             PreparedStatement psmt = conn.prepareStatement(qur)) {

          

            psmt.setString(1, sid);
            ResultSet rs = psmt.executeQuery();

            if (rs.next()) {
                System.out.println("ID: " + rs.getString("stdId"));
                System.out.println("Name: " + rs.getString("stdname"));
                System.out.println("Email: " + rs.getString("stdmail"));
                System.out.println("DOB: " + rs.getString("stddob"));
            } else {
                System.out.println("No student found with ID " + sid);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
