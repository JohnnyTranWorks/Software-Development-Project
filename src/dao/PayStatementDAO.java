package dao;

import models.PayStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayStatementDAO {
    
    public void insertPayStatement(PayStatement statement) throws SQLException {
        String query = "INSERT INTO payroll (emp_id, pay_date, gross_pay, deductions, net_pay) " +
                      "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, statement.getEmpId());
            stmt.setDate(2, Date.valueOf(statement.getPayDate()));
            stmt.setDouble(3, statement.getGrossPay());
            stmt.setDouble(4, statement.getDeductions());
            stmt.setDouble(5, statement.getNetPay());
            stmt.executeUpdate();
        }
    }
    
    public List<PayStatement> getPayStatementsByEmployeeId(int empId) throws SQLException {
        String query = "SELECT * FROM payroll WHERE emp_id = ? ORDER BY pay_date DESC";
        List<PayStatement> statements = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                PayStatement ps = new PayStatement();
                ps.setPayId(rs.getInt("pay_id"));
                ps.setEmpId(rs.getInt("emp_id"));
                ps.setPayDate(rs.getDate("pay_date").toLocalDate());
                ps.setGrossPay(rs.getDouble("gross_pay"));
                ps.setDeductions(rs.getDouble("deductions"));
                ps.setNetPay(rs.getDouble("net_pay"));
                statements.add(ps);
            }
        }
        return statements;
    }
}