package com.retail;

/**
 * Клас, що представляє замовлення
 */
public class Order {
    private double totalAmount;
    private String orderDate;

    public Order(double totalAmount, String orderDate) {
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        System.out.println("Створено замовлення на суму: " + totalAmount + " від " + orderDate);
    }

    public void displayOrderInfo() {
        System.out.println("Замовлення:");
        System.out.println("Загальна сума: " + totalAmount);
        System.out.println("Дата: " + orderDate);
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }
}
