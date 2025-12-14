package com.payroll;

/**
 * Клас, що представляє квитанцію про зарплату
 */
public class Paycheck {
    public double amount;
    public String payDate;

    public Paycheck(double amount, String payDate) {
        this.amount = amount;
        this.payDate = payDate;
        System.out.println("Створено квитанцію на суму: " + amount + " від " + payDate);
    }

    public void displayPaycheckInfo() {
        System.out.println("Квитанція про зарплату:");
        System.out.println("Сума: " + amount);
        System.out.println("Дата: " + payDate);
    }
}
