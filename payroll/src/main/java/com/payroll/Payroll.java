package com.payroll;

import java.util.List;

import com.google.inject.Inject;

/**
 * Клас для обробки зарплати працівників
 */
public class Payroll {
    private List<Person> employees;
    private Paycheck paycheck;
    private final PaymentService paymentService;

    @Inject
    public Payroll(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void setEmployees(List<Person> employees) {
        this.employees = employees;
    }

    public void processPayroll(double salary) {
        System.out.println("Обробка зарплати...");
        
        for (Person person : employees) {
            double totalAmount = salary;
            
            if (person instanceof BonusEligible) {
                BonusEligible employee = (BonusEligible) person;
                totalAmount += employee.getBonus();
            }
            
            this.paycheck = new Paycheck(totalAmount, "28.09.2025");
            System.out.println("Зарплата оброблена для: " + person.getName());
            this.paycheck.displayPaycheckInfo();
            
            // Збереження квитанції в базу даних
            paymentService.savePaycheck(this.paycheck);
        }
    }

    /**
     * Отримує всі квитанції з бази даних.
     * 
     * @return список всіх квитанцій
     */
    public List<Paycheck> getAllPaychecks() {
        return paymentService.getAllPaychecks();
    }
}
