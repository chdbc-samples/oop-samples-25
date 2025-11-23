package com.payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import com.payroll.webserver.JavalinWebServer;
import com.payroll.webserver.WebServer;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

/**
 * Конфігураційний модуль Google Guice для налаштування Dependency Injection.
 * 
 * Реалізує принципи Inversion of Control (IoC) та Dependency Injection (DI):
 * - Клієнти (наприклад, PaymentService) залежать від абстракції Connection, а не від конкретного драйвера бази даних
 * - Контроль над створенням залежностей передано фреймворку Guice
 * - Забезпечує гнучкість: для зміни бази даних (SQLite → PostgreSQL/MySQL) достатньо змінити лише JDBC URL
 *   та драйвер у pom.xml, без модифікації бізнес-логіки
 */
public class PayrollModule extends AbstractModule {
    
    /**
     * Конфігурує базові прив'язки для Dependency Injection.
     * Прив'язує JDBC URL для підключення до бази даних SQLite.
     */
    @Override
    protected void configure() {
        bind(String.class)
            .annotatedWith(Names.named("JDBC URL"))
            .toInstance("jdbc:sqlite:target/payroll.db");
    }

    /**
     * Надає єдиний екземпляр з'єднання з базою даних (Singleton).
     * Створює з'єднання з SQLite та ініціалізує структуру таблиць.
     * Реалізує принципи Inversion of Control та Dependency Injection: клієнти залежать від абстракції Connection,
     * а не від конкретного драйвера бази даних, що забезпечує гнучкість і можливість заміни реалізації.
     *
     * @param url JDBC URL для підключення до бази даних
     * @return з'єднання з базою даних
     * @throws RuntimeException якщо не вдалося встановити з'єднання
     */
    @Provides
    @Singleton
    Connection provideConnection(@Named("JDBC URL") String url) {
        try {
            Connection connection = DriverManager.getConnection(url);
            createTableIfNotExists(connection);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Не вдалося створити з'єднання з базою даних", e);
        }
    }

    /**
     * Створює таблицю "paychecks" у базі даних, якщо вона ще не існує.
     * Таблиця містить такі колонки:
     * - amount: значення типу REAL, яке не може бути null, що представляє суму виплати.
     * - pay_date: значення типу TEXT, яке не може бути null, що представляє дату виплати.
     *
     * @param connection з'єднання з базою даних, яке використовується для створення таблиці
     * @throws RuntimeException у разі виникнення помилки під час створення таблиці
     */
    private void createTableIfNotExists(Connection connection) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS paychecks (" +
                                "amount REAL NOT NULL, " +
                                "pay_date TEXT NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException("Не вдалося створити таблицю", e);
        }
    }

    /**
     * Надає екземпляр WebServer для веб-інтерфейсу.
     * Використовує Javalin як реалізацію, але може бути легко замінений на інший фреймворк
     * шляхом зміни лише цього методу.
     *
     * @return екземпляр WebServer
     */
    @Provides
    @Singleton
    WebServer provideWebServer() {
        return new JavalinWebServer();
    }
}
