# Програма підприємства

Це імітаційний прототип об'єктно-орієнтованої програми підприємства на Java.

## Структура проєкту

- `com.payroll.BonusEligible` - інтерфейс для роботи з бонусами
- `com.payroll.Person` - абстрактний клас особи
- `com.payroll.Employee` - клас працівника
- `com.payroll.Paycheck` - клас квитанції про зарплату
- `com.payroll.Payroll` - клас для обробки зарплати (Model)
- `com.payroll.PayrollController` - контролер MVC для координації View та Model
- `com.payroll.PayrollWebView` - веб-інтерфейс (View) для REST API
- `com.payroll.PaymentService` - сервіс для збереження квитанцій у базу даних SQLite
- `com.payroll.Main` - головний клас: консольне введення даних + запуск веб-сервера
- `com.payroll.PayrollModule` - конфігураційний модуль для Guice, який налаштовує та впроваджує залежності
- `com.payroll.webserver.*` - абстракції веб-сервера (WebServer, HttpContext, Response, JavalinWebServer)

## Компіляція

```bash
cd payroll
mvn clean install
```

## Запуск

```bash
cd payroll
mvn exec:java -Dexec.mainClass="com.payroll.Main"
```

Програма спочатку створить дані про працівників, потім запустить веб-сервер на `http://localhost:8080`.

Для перегляду даних відкрийте `src/main/resources/index.html` через розширення Live Server у VS Code.

## Опис роботи

Програма демонструє:
1. Створення працівників з різними бонусами
2. Виведення інформації про працівників
3. Обробку зарплати з урахуванням бонусів
4. Виведення квитанцій про зарплату
5. Збереження квитанцій у базу даних SQLite
6. Веб-інтерфейс для перегляду збережених квитанцій (шаблон MVC)

## База даних

**Драйвер:** SQLite JDBC (`org.xerial:sqlite-jdbc:3.36.0.3`)  
**Шлях до бази:** `target/payroll.db`  
**Таблиця `paychecks`:**
- `amount` (REAL) - сума виплати
- `pay_date` (TEXT) - дата виплати
