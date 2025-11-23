package com.payroll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

/**
 * Сервіс для роботи з платіжними відомостями в базі даних
 */
public class PaymentService {
    private Connection connection;

    /**
     * Конструктор з впровадженням залежності від драйвера бази даних.
     *
     * @param connection з'єднання з базою даних
     */
    @Inject
    public PaymentService(Connection connection) {
        this.connection = connection;
    }

    protected Connection getConnection() {
        return connection;
    }

    /**
     * Метод для збереження об'єкта Paycheck в базі даних.
     *
     * Цей метод приймає об'єкт Paycheck, створює SQL-запит для вставки даних
     * про зарплату в базу даних і виконує цей запит.
     *
     * @param paycheck об'єкт Paycheck, який містить дані про зарплату
     * @throws RuntimeException якщо виникає помилка під час збереження даних
     */
    public void savePaycheck(Paycheck paycheck) {
        String sql = "INSERT INTO paychecks (amount, pay_date) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, paycheck.getAmount());
            statement.setString(2, paycheck.getPayDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Не вдалося зберегти квитанцію", e);
        }
    }

    /**
     * Отримує всі квитанції з бази даних.
     *
     * @return список всіх квитанцій
     */
    public List<Paycheck> getAllPaychecks() {
        List<Paycheck> paychecks = new ArrayList<>();
        String sql = "SELECT amount, pay_date FROM paychecks";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                double amount = resultSet.getDouble("amount");
                String payDate = resultSet.getString("pay_date");
                paychecks.add(new Paycheck(amount, payDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не вдалося отримати квитанції", e);
        }

        return paychecks;
    }
}
