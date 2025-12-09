package services;

import models.Employee;
import dao.EmployeeDAO;

import java.util.List;

public class EmployeeManager {

    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public Employee getEmployeeById(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    public List<Employee> searchByName(String name) {
        return employeeDAO.searchByName(name);
    }

    public void updateSalaryRange(double percent, double low, double high) {
        employeeDAO.updateSalaryRange(percent, low, high);
    }
}
