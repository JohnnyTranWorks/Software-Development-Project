package models;

public class Employee extends Person {
    private int empId;
    private double baseSalary;

    public Employee(int empId, String fn, String ln, double salary) {
        super(fn, ln);
        this.empId = empId;
        this.baseSalary = salary;
    }

    public int getEmpId() { return empId; }
    public double getBaseSalary() { return baseSalary; }

    public void setBaseSalary(double salary) {
        this.baseSalary = salary;
    }
}
