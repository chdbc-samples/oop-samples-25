package com.payroll;

import com.google.inject.Inject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentService {

    private final Connection connection;

    @Inject
    public PaymentService(Connection connection) {
        this.connection = connection;
    }

    public void savePaycheck(Paycheck paycheck) {
        String sql = "INSERT INTO paychecks (amount, pay_date) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, paycheck.amount);
            pstmt.setString(2, paycheck.payDate);
            pstmt.executeUpdate();
            System.out.println("Збережено квитанцію: " + paycheck.amount + " від " + paycheck.payDate);
        } catch (SQLException e) {
            System.err.println("Помилка при збереженні квитанції: " + e.getMessage());
        }
    }
}