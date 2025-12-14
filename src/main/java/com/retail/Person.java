package com.retail;

/**
 * Абстрактний клас, що представляє особу
 */
public abstract class Person {
    // Ім'я особи
    private String name;

    // Конструктор за замовчуванням
    public Person() {
        this("Без імені");
        System.out.println("Створено нову особу");
    }

    // Конструктор з параметром
    public Person(String name) {
        this.name = name;
        System.out.println("Створено особу: " + name);
    }

    // Отримати ім'я
    public String getName() {
        return name;
    }

    // Встановити ім'я
    public void setName(String name) {
        this.name = name;
        System.out.println("Змінено ім'я на: " + name);
    }

    // Абстрактний метод для виведення інформації
    public abstract void displayInfo();

    // Фінальний метод для отримання повної інформації
    public final String getFullDetails() {
        return "Особа: " + name;
    }

    // Статичний метод для виведення інформації про особу
    public static void printInfo(Person person) {
        System.out.println("Інформація про особу:");
        person.displayInfo();
    }
}
