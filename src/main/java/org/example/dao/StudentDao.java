package org.example.dao;

import org.example.entity.Student;
import org.example.repository.BaseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements BaseDao<Student> {
    private static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
        return null;
    }
    @Override
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        String query = "SELECT * FROM student";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Student student = new Student();
                student.setStudenId(rs.getInt("id"));
                student.setStudentName(rs.getString("name"));
                student.setStudentAge(rs.getInt("age"));
                list.add(student);
            }

        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }

        return list;
    }


    @Override
    public boolean add(Student student) {
        String query = "INSERT INTO student(name, age) VALUES(?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, student.getStudentName());
            stmt.setInt(2, student.getStudentAge());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public Student findById(int id) {
        String query = "SELECT * FROM student WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setStudenId(rs.getInt("id"));
                student.setStudentName(rs.getString("name"));
                student.setStudentAge(rs.getInt("age"));
                return student;
            }
        } catch (SQLException e) {
            System.err.println("Error finding student by ID: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return null;
    }

    @Override
    public boolean update(Student student) {
        String query = "UPDATE student SET name = ?, age = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, student.getStudentName());
            stmt.setInt(2, student.getStudentAge());
            stmt.setInt(3, student.getStudentId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM student WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return false;
    }

}
