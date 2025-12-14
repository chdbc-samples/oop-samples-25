package com.payroll;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Головний клас для демонстрації роботи програми.
 * 
 * Реалізація шаблону MVC (Model-View-Controller):
 * - Model: Payroll (бізнес-логіка обробки зарплат), Paycheck (дані), PaymentService (доступ до БД)
 * - View: PayrollWebView (REST API), index.html + script.js (веб-інтерфейс)
 * - Controller: PayrollController (координація між View та Model)
 * 
 * Правильний потік даних у MVC: View → Controller → Model → Service → Database
 * Веб-частина програми реалізує MVC наступним чином: PayrollWebView → PayrollController → Payroll → PaymentService → база даних SQLite
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
        
        // У навчальних цілях цей код залишено без змін, хоча з точки зору MVC мав би викликатись Main → PayrollController → Payroll.
        payroll.processPayroll(5000.0);
        
        // Потім запускаємо веб-сервер для перегляду
        runWebMode(injector);
    }
    

    
    /**
     * Веб-режим з REST API для перегляду даних.
     */
    private static void runWebMode(Injector injector) {
        PayrollWebView webView = injector.getInstance(PayrollWebView.class);
        webView.start(8080);
    }
}
