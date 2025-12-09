package models;

import java.time.LocalDate;

public class PayStatement {
    private int empId;
    private LocalDate payDate;
    private double amount;

    public PayStatement(int empId, LocalDate payDate, double amount) {
        this.empId = empId;
        this.payDate = payDate;
        this.amount = amount;
    }

    public int getEmpId() { return empId; }
    public LocalDate getPayDate() { return payDate; }
    public double getAmount() { return amount; }
}
