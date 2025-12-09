package dao;

import models.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public Employee getEmployeeById(int empId) {
        // implement later
        return null;
    }

    public List<Employee> searchByName(String name) {
        return new ArrayList<>();
    }

    public void updateSalaryRange(double percent, double low, double high) {
        // implement later
    }
}
