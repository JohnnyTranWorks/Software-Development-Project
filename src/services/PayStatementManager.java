package services;

import models.PayStatement;
import dao.PayStatementDAO;

import java.util.List;

public class PayStatementManager {

    private PayStatementDAO dao = new PayStatementDAO();

    public List<PayStatement> getPayHistory(int empId) {
        return dao.getPayStatementsByEmployee(empId);
    }
}
