# Copilot Instructions — Практична робота №3 (Retail, Google Guice + SQLite)

## ВАЖЛИВО

ТВІЙ ВАРІАНТ ЗА ТАБЛИЦЕЮ — **retail**.

Потрібно **адаптувати вже готовий проєкт Payroll** під предметну область **Retail**, НЕ змінюючи логіку DI (Guice + SQLite).

Твоя задача як Copilot:

* перейменувати класи, пакети та БД під **retail**
* зберегти принцип інверсії залежностей
* зберегти роботу програми

---

## ПОТОЧНИЙ СТАН ПРОЄКТУ (що вже є)

Проєкт містить класи (Payroll-домен):

* `Person`
* `Employee`
* `BonusEligible`
* `Paycheck`
* `Payroll`
* `PaymentService`
* `PayrollModule`
* `Main`

Використано:

* Google Guice
* SQLite
* Constructor Injection + Setter Injection

---

## ЦІЛЬ

Привести проєкт у відповідність до варіанту **retail**:

* Maven-проєкт: `retail`
* пакет: `com.retail`
* предметна область: замовлення / постачання

---

## КРОК 1. Перейменування пакету

Зміни пакет у **ВСІХ Java-файлах**:

```java
package com.retail;
```

Онови імпорти відповідно.

---

## КРОК 2. Перейменування класів (ОБОВʼЯЗКОВО)

Виконай точне перейменування:

| Було (Payroll) | Стало (Retail) |
| -------------- | -------------- |
| Payroll        | RetailSystem   |
| Paycheck       | Order          |
| PaymentService | OrderService   |
| PayrollModule  | RetailModule   |

`Main` залишити без перейменування.

---

## КРОК 3. Адаптація предметної області

### Клас Order (колишній Paycheck)

* поля:

```java
private double totalAmount;
private String orderDate;
```

### Клас RetailSystem (колишній Payroll)

* має **композицію** з `Order`
* використовує `OrderService` через DI
* створює замовлення та передає його в сервіс збереження

---

## КРОК 4. Guice-модуль

Клас `RetailModule`:

* JDBC URL: `jdbc:sqlite:target/retail.db`
* створює таблицю:

```sql
orders(
  total_amount REAL,
  order_date TEXT
)
```

* `Connection` надається як `@Singleton`

---

## КРОК 5. OrderService (робота з БД)

Клас `OrderService`:

* отримує `Connection` через **constructor injection**
* метод:

```java
void saveOrder(Order order)
```

* виконує `INSERT` у таблицю `orders`

---

## КРОК 6. DI через конструктор

У класі `RetailSystem`:

* використовуй **constructor injection** для `OrderService`
* викликай `orderService.saveOrder(order)`

---

## КРОК 7. DI через setter-метод

Після реалізації constructor injection:

* закоментуй конструктор з параметрами
* додай:

```java
@Inject
public void setOrderService(OrderService orderService)
```

---

## КРОК 8. Main.java

У `Main.java`:

* використовуй:

```java
Injector injector = Guice.createInjector(new RetailModule());
RetailSystem retailSystem = injector.getInstance(RetailSystem.class);
```

---

## КРОК 9. UML (docs/class.puml)

Онови UML-діаграму класів:

* `RetailSystem o-- Order`
* `RetailSystem --> OrderService : «inject»`
* `OrderService --> Connection`
* `RetailModule ..> Connection`

---

## КРОК 10. README.md

Онови інформацію:

* назва проєкту: **Retail**
* пакет: `com.retail`
* БД: `target/retail.db`

Команди:

```bash
mvn clean install
mvn exec:java -D"exec.mainClass=com.retail.Main"
```

---

## ВИМОГИ

* НЕ змінювати логіку Dependency Injection
* НЕ ламати структуру Maven
* НЕ залишати згадок `payroll` у коді

---

## ОЧІКУВАНИЙ РЕЗУЛЬТАТ

* Проєкт відповідає варіанту **retail** з таблиці
* SQLite файл `target/retail.db` створюється
* Таблиця `orders` заповнюється
* DI реалізований через constructor і setter

---

Виконуй кроки ПОСЛІДОВНО.
Працюй лише з цим репозиторієм.
Нічого зайвого не додавай.
