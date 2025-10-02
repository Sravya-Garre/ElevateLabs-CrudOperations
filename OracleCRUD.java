package com.nt.main;

import java.sql.*;
import java.util.Scanner;

public class OracleCRUD{
    // Database connection details
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:ORCL"; 
    static final String USER = "system";  
    static final String PASS = "sravya";  

    public static void main(String[] args) {
        Connection conn = null;
        Scanner sc = new Scanner(System.in);

        try {
            // Load JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to Oracle database!");

            // Ensure table exists
            Statement stmt = conn.createStatement();
            try {
                stmt.executeUpdate("CREATE TABLE employees (id NUMBER PRIMARY KEY, name VARCHAR2(50), salary NUMBER)");
                System.out.println("✅ Table created.");
            } catch (SQLException e) {
                // If table already exists, ignore
            }

            int choice;
            do {
                System.out.println("\n==== Employee Management System ====");
                System.out.println("1. Insert Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee Salary");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        insertEmployee(conn, sc);
                        break;
                    case 2:
                        viewEmployees(conn);
                        break;
                    case 3:
                        updateEmployee(conn, sc);
                        break;
                    case 4:
                        deleteEmployee(conn, sc);
                        break;
                    case 5:
                        System.out.println("Exiting program...");
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } while (choice != 5);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
                System.out.println("Connection closed.");
            } catch (SQLException se) {
                se.printStackTrace();
            }
            sc.close();
        }
    }

    // Insert Employee
    private static void insertEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        String sql = "INSERT INTO employees (id, name, salary) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.setString(2, name);
        pstmt.setDouble(3, salary);

        int rows = pstmt.executeUpdate();
        if (rows > 0) System.out.println("Employee inserted successfully.");
    }

    // View Employees
    private static void viewEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employees";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\n--- Employee Records ---");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") +
                               ", Name: " + rs.getString("name") +
                               ", Salary: " + rs.getDouble("salary"));
        }
    }

    // Update Employee
    private static void updateEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();
        System.out.print("Enter new Salary: ");
        double salary = sc.nextDouble();

        String sql = "UPDATE employees SET salary=? WHERE id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDouble(1, salary);
        pstmt.setInt(2, id);

        int rows = pstmt.executeUpdate();
        if (rows > 0) System.out.println("Employee updated successfully.");
        else System.out.println(" Employee not found.");
    }

    // Delete Employee
    private static void deleteEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM employees WHERE id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);

        int rows = pstmt.executeUpdate();
        if (rows > 0) System.out.println("Employee deleted successfully.");
        else System.out.println("Employee not found.");
    }
}
