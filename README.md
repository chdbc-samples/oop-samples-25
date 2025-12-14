# Retail System with Dependency Injection

Це об'єктно-орієнтована програма для обробки замовлень у системі роздрібної торгівлі з використанням принципів інверсії залежностей (DIP) через Google Guice та збереженням даних у базі даних SQLite.

## Призначення проєкту

Проєкт демонструє реалізацію DIP для збереження об'єктів `Order` у SQLite двома способами:
- Впровадження залежностей через конструктор
- Впровадження залежностей через setter-метод

## Структура проєкту

di
Java-файли розташовані у `src/main/java/com/retail/`:
- `BonusEligible.java` - інтерфейс для роботи з бонусами
- `Person.java` - абстрактний клас особи
- `Employee.java` - клас працівника
- `Order.java` - клас замовлення
- `RetailSystem.java` - клас для обробки системи з інжекцією залежностей
- `OrderService.java` - сервіс для збереження у БД
- `RetailModule.java` - Guice модуль для конфігурації залежностей
- `Main.java` - головний клас з демонстрацією роботи

## Команди для збірки та запуску

```bash
mvn clean install
mvn exec:java -D"exec.mainClass=com.retail.Main"
- `com.payroll.BonusEligible` - інтерфейс для роботи з бонусами
- `com.payroll.Person` - абстрактний клас особи
- `com.payroll.Employee` - клас працівника
- `com.payroll.Paycheck` - клас квитанції про зарплату
- `com.payroll.Payroll` - клас для обробки зарплати
- `com.payroll.PaymentService` - сервіс для збереження квитанцій у базу даних SQLite
- `com.payroll.Main` - головний клас з демонстрацією роботи
- `com.payroll.PayrollModule` - конфігураційний модуль для Guice, який налаштовує та впроваджує залежності

## Компіляція

```bash
cd payroll
mvn clean install
```

## Запуск

```bash
mvn exec:java -Dexec.mainClass="com.payroll.Main"di
```

## Опис роботи

Програма демонструє:
1. Створення працівників з різними бонусами
2. Виведення інформації про працівників
di
3. Обробку замовлень з урахуванням бонусів
4. Збереження замовлень у SQLite базі даних `target/retail.db`
3. Обробку зарплати з урахуванням бонусів
4. Виведення квитанцій про зарплату
5. Збереження квитанцій у базу даних SQLite

## База даних

**Драйвер:** SQLite JDBC (`org.xerial:sqlite-jdbc:3.36.0.3`)  
**Шлях до бази:** `target/payroll.db`  
**Таблиця `paychecks`:**
- `amount` (REAL) - сума виплати
- `pay_date` (TEXT) - дата виплати
di
