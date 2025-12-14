package com.retail;

import com.google.inject.Inject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderService {

    private final Connection connection;

    @Inject
    public OrderService(Connection connection) {
        this.connection = connection;
    }

    public void saveOrder(Order order) {
        String sql = "INSERT INTO orders (total_amount, order_date) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, order.getTotalAmount());
            pstmt.setString(2, order.getOrderDate());
            pstmt.executeUpdate();
            System.out.println("Збережено замовлення: " + order.getTotalAmount() + " від " + order.getOrderDate());
        } catch (SQLException e) {
            System.err.println("Помилка при збереженні замовлення: " + e.getMessage());
        }
    }
}