package com.retail;

/**
 * Клас, що представляє працівника
 */
public class Employee extends Person implements BonusEligible {
    private double bonus;

    // Конструктор за замовчуванням
    public Employee() {
        super();
        this.bonus = DEFAULT_BONUS;
    }

    // Конструктор з ім'ям
    public Employee(String name) {
        super(name);
        this.bonus = DEFAULT_BONUS;
    }

    // Конструктор з ім'ям та бонусом
    public Employee(String name, double bonus) {
        super(name);
        this.bonus = bonus;
    }

    // Реалізація методу з інтерфейсу BonusEligible
    @Override
    public double getBonus() {
        return bonus;
    }

    // Реалізація методу з інтерфейсу BonusEligible
    @Override
    public void setBonus(double bonus) {
        this.bonus = bonus;
        System.out.println("Встановлено бонус для " + getName() + ": " + bonus);
    }

    // Реалізація абстрактного методу з класу Person
    @Override
    public void displayInfo() {
        System.out.println("Працівник: " + getName() + ", Бонус: " + bonus);
    }
}
