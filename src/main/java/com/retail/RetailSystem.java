package com.retail;

import com.google.inject.Inject;
import java.util.List;

/**
 * Клас для обробки системи роздрібної торгівлі
 */
public class RetailSystem {
    private List<Person> employees;
    private Order order;
    private OrderService orderService;

    @Inject
    public RetailSystem(OrderService orderService) {
        this.orderService = orderService;
        System.out.println("Створено систему через конструктор");
    }

    // Закоментовано setter injection
    // @Inject
    // public void setOrderService(OrderService orderService) {
    //     this.orderService = orderService;
    //     System.out.println("Встановлено OrderService через setter");
    // }

    public void setEmployees(List<Person> employees) {
        this.employees = employees;
        System.out.println("Встановлено працівників для системи: " + employees.size());
    }

    public void processOrders(double baseAmount) {
        if (employees == null) {
            System.out.println("Працівники не встановлені!");
            return;
        }
        System.out.println("Обробка замовлень...");
        
        for (Person person : employees) {
            double totalAmount = baseAmount;
            
            if (person instanceof BonusEligible) {
                BonusEligible employee = (BonusEligible) person;
                totalAmount += employee.getBonus();
            }
            
            this.order = new Order(totalAmount, "28.09.2025");
            System.out.println("Замовлення оброблено для: " + person.getName());
            this.order.displayOrderInfo();
            this.orderService.saveOrder(this.order);
        }
    }
}
