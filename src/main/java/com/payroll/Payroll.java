package com.payroll;

import com.google.inject.Inject;
import java.util.List;

/**
 * Клас для обробки зарплати працівників
 */
public class Payroll {
    private List<Person> employees;
    private Paycheck paycheck;
    private PaymentService paymentService;

    @Inject
    public Payroll(PaymentService paymentService) {
        this.paymentService = paymentService;
        System.out.println("Створено відомість");
    }

    // Закоментовано для setter injection
    // public Payroll() {
    //     System.out.println("Створено відомість через пустий конструктор");
    // }

    // @Inject
    // public void setPaymentService(PaymentService paymentService) {
    //     this.paymentService = paymentService;
    //     System.out.println("Встановлено PaymentService через setter");
    // }

    public void setEmployees(List<Person> employees) {
        this.employees = employees;
        System.out.println("Встановлено працівників для відомості: " + employees.size());
    }

    public void processPayroll(double salary) {
        if (employees == null) {
            System.out.println("Працівники не встановлені!");
            return;
        }
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
            this.paymentService.savePaycheck(this.paycheck);
        }
    }
}
