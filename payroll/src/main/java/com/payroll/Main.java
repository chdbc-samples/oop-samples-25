package com.payroll;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Головний клас для демонстрації роботи програми
 */
public class Main {
    public static void main(String[] args) {
        // Створюємо працівників
        Employee emp1 = new Employee("Іван Петренко", 1000.0);
        Employee emp2 = new Employee("Марія Коваленко", 500.0);
        
        // Виводимо інформацію про працівників
        System.out.println("\nІнформація про працівників:");
        emp1.displayInfo();
        emp2.displayInfo();
        
        // Створюємо список працівників
        List<Person> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
        
        // Створюємо Injector та отримуємо екземпляр класу Payroll
        Injector injector = Guice.createInjector(new PayrollModule());
        Payroll payroll = injector.getInstance(Payroll.class);
        
        // Зберігаємо список працівників
        payroll.setEmployees(employees);
        
        // Обробляємо зарплату
        payroll.processPayroll(5000.0);
    }
}
