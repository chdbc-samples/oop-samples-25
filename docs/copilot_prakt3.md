# Copilot Instructions — Практична робота №3 (Google Guice + SQLite)

## Контекст проєкту

Ти працюєш у Maven-проєкті **Payroll** з пакетом `com.payroll`.
Проєкт уже містить класи з Практичної роботи №2:

* `Person`
* `Employee`
* `BonusEligible`
* `Paycheck`
* `Payroll`
* `Main`

Мета — **реалізувати принцип інверсії залежностей (DIP)** для збереження об'єктів `Paycheck` у базі даних SQLite **двома способами**:

1. Впровадження залежностей через **конструктор**
2. Впровадження залежностей через **setter-метод**

Використовувати **Google Guice**.

---

## КРОК 1. Оновлення pom.xml

Додай у `<dependencies>` такі залежності:

```xml
<!-- Google Guice -->
<dependency>
    <groupId>com.google.inject</groupId>
    <artifactId>guice</artifactId>
    <version>5.1.0</version>
</dependency>

<!-- SQLite JDBC -->
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.36.0.3</version>
</dependency>
```

---

## КРОК 2. Створи Guice-модуль

Створи файл `PayrollModule.java` у пакеті `com.payroll`.

Вимоги:

* наслідує `AbstractModule`
* біндить JDBC URL `jdbc:sqlite:target/payroll.db`
* надає `Connection` як `@Singleton`
* створює таблицю `paychecks(amount REAL, pay_date TEXT)` якщо її не існує

---

## КРОК 3. Створи сервіс доступу до БД

Створи клас `PaymentService` у пакеті `com.payroll`.

Вимоги:

* має поле `Connection`
* отримує `Connection` через **constructor injection** (`@Inject`)
* містить метод:

```java
void savePaycheck(Paycheck paycheck)
```

* метод виконує `INSERT` у таблицю `paychecks`

---

## КРОК 4. Constructor Injection (основна частина)

Модифікуй клас `Payroll`:

* прибери створення `PaymentService` через `new`
* додай поле `PaymentService`
* реалізуй **конструктор з @Inject**, який приймає `PaymentService`
* у методі обробки зарплати викликай `paymentService.savePaycheck(paycheck)`

`Payroll` має **композицію з `Paycheck`**.

---

## КРОК 5. Онови Main.java

У `Main.java`:

* НЕ використовуй оператор `new Payroll(...)`
* створи `Injector` через:

```java
Injector injector = Guice.createInjector(new PayrollModule());
```

* отримай `Payroll` через:

```java
Payroll payroll = injector.getInstance(Payroll.class);
```

* виклич метод обробки зарплати

---

## КРОК 6. Setter Injection (друга частина роботи)

Модифікуй `Payroll`:

* закоментуй конструктор з параметрами
* додай пустий конструктор
* додай setter-метод:

```java
@Inject
public void setPaymentService(PaymentService paymentService)
```

* логіка збереження `Paycheck` залишається

---

## КРОК 7. UML-діаграма (class.puml)

Онови діаграму класів так, щоб вона відображала:

* залежність `Payroll --> PaymentService`
* залежність `PaymentService --> Connection`
* композицію `Payroll o-- Paycheck`

Для setter injection окремо показати впровадження через метод.

---

## КРОК 8. README.md

Онови README так, щоб він містив:

* призначення проєкту
* шлях до Java-файлів
* команди:

```bash
mvn clean install
mvn exec:java -D"exec.mainClass=com.payroll.Main"
```

---

## КРОК 9. Git

* додай `target/` у `.gitignore`
* зроби **2 окремі коміти**:

### Коміт 1

```
Робота з базою даних із використанням впровадження залежностей через конструктор
```

### Коміт 2

```
Робота з базою даних із використанням впровадження залежностей через setter-метод класу
```

---

## Очікуваний результат

* програма успішно запускається
* вивід у консолі такий самий, як у Практичній №2
* файл `target/payroll.db` містить записи в таблиці `paychecks`
* код відповідає принципу **Dependency Inversion Principle**

---

❗ Дотримуйся існуючої структури проєкту та пакету `com.payroll`.
❗ Не змінюй бізнес-логіку з Практичної роботи №2, лише додай роботу з БД через Guice.
