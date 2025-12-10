package dao;

import models.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    
    public boolean employeeExists(int empId) throws SQLException {
        String query = "SELECT COUNT(*) FROM employees WHERE emp_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        }
    }
    
    public void insertEmployee(Employee employee) throws SQLException {
        String query = "INSERT INTO employees (emp_id, first_name, last_name, dob, ssn, " +
                      "address, division, job_title, salary, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, employee.getEmpId());
            stmt.setString(2, employee.getFirstName());
            stmt.setString(3, employee.getLastName());
            stmt.setDate(4, Date.valueOf(employee.getDob()));
            stmt.setString(5, employee.getSsn());
            stmt.setString(6, employee.getAddress());
            stmt.setString(7, employee.getDivision());
            stmt.setString(8, employee.getJobTitle());
            stmt.setDouble(9, employee.getSalary());
            stmt.setString(10, employee.getStatus());
            stmt.executeUpdate();
        }
    }
    
    public Employee getEmployeeById(int empId) throws SQLException {
        String query = "SELECT * FROM employees WHERE emp_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extractEmployee(rs);
            }
            return null;
        }
    }
    
    public List<Employee> searchByName(String name) throws SQLException {
        String query = "SELECT * FROM employees WHERE first_name LIKE ? OR last_name LIKE ?";
        List<Employee> employees = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            String searchTerm = "%" + name + "%";
            stmt.setString(1, searchTerm);
            stmt.setString(2, searchTerm);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                employees.add(extractEmployee(rs));
            }
        }
        return employees;
    }
    
    public List<Employee> searchByDOB(Date dob) throws SQLException {
        String query = "SELECT * FROM employees WHERE dob = ?";
        List<Employee> employees = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setDate(1, dob);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                employees.add(extractEmployee(rs));
            }
        }
        return employees;
    }
    
    public List<Employee> searchBySSN(String ssn) throws SQLException {
        String query = "SELECT * FROM employees WHERE ssn = ?";
        List<Employee> employees = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, ssn);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                employees.add(extractEmployee(rs));
            }
        }
        return employees;
    }
    
    public void updateEmployee(Employee employee) throws SQLException {
        String query = "UPDATE employees SET first_name = ?, last_name = ?, dob = ?, " +
                      "ssn = ?, address = ?, division = ?, job_title = ?, salary = ?, status = ? " +
                      "WHERE emp_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setDate(3, Date.valueOf(employee.getDob()));
            stmt.setString(4, employee.getSsn());
            stmt.setString(5, employee.getAddress());
            stmt.setString(6, employee.getDivision());
            stmt.setString(7, employee.getJobTitle());
            stmt.setDouble(8, employee.getSalary());
            stmt.setString(9, employee.getStatus());
            stmt.setInt(10, employee.getEmpId());
            stmt.executeUpdate();
        }
    }
    
    public List<Employee> getEmployeesBySalaryRange(double minSalary, double maxSalary) throws SQLException {
        String query = "SELECT * FROM employees WHERE salary BETWEEN ? AND ?";
        List<Employee> employees = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setDouble(1, minSalary);
            stmt.setDouble(2, maxSalary);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                employees.add(extractEmployee(rs));
            }
        }
        return employees;
    }
    
    public void updateSalary(int empId, double newSalary) throws SQLException {
        String query = "UPDATE employees SET salary = ? WHERE emp_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setDouble(1, newSalary);
            stmt.setInt(2, empId);
            stmt.executeUpdate();
        }
    }
    
    private Employee extractEmployee(ResultSet rs) throws SQLException {
        Employee emp = new Employee();
        emp.setEmpId(rs.getInt("emp_id"));
        emp.setFirstName(rs.getString("first_name"));
        emp.setLastName(rs.getString("last_name"));
        emp.setDob(rs.getDate("dob").toLocalDate());
        emp.setSsn(rs.getString("ssn"));
        emp.setAddress(rs.getString("address"));
        emp.setDivision(rs.getString("division"));
        emp.setJobTitle(rs.getString("job_title"));
        emp.setSalary(rs.getDouble("salary"));
        emp.setStatus(rs.getString("status"));
        return emp;
    }
}