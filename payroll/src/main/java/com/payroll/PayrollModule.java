package com.payroll;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class PayrollModule extends AbstractModule {
    @Provides
    List<Person> provideEmployees() {
        // Створюємо працівників
        Person emp1 = new Employee("Іван Петренко", 1000.0);
        Person emp2 = new Employee("Марія Коваленко", 500.0);
       
        // Виводимо інформацію про працівників
        System.out.println("\nІнформація про працівників:");
        emp1.displayInfo();
        emp2.displayInfo();
       
        // Створюємо список працівників
        List<Person> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
       
        return employees;
    }
}
