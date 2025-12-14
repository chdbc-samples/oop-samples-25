package com.payroll;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.ArrayList;
import java.util.List;

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
        
        // Створюємо Injector
        Injector injector = Guice.createInjector(new PayrollModule());
        
        // Отримуємо Payroll через injector
        Payroll payroll = injector.getInstance(Payroll.class);
        payroll.setEmployees(employees);
        
        // Обробляємо зарплату
        System.out.println("\nОбробка зарплати:");
        payroll.processPayroll(5000.0);
    }
}
