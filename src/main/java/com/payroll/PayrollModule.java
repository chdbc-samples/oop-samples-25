package com.payroll;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PayrollModule extends AbstractModule {

    private static final String JDBC_URL = "jdbc:sqlite:target/payroll.db";

    @Override
    protected void configure() {
        // Ніякі додаткові біндинги не потрібні, оскільки використовуємо @Provides для Connection
    }

    @Provides
    @Singleton
    public Connection provideConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL);
        createTableIfNotExists(connection);
        return connection;
    }

    private void createTableIfNotExists(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS paychecks (amount REAL, pay_date TEXT)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }
}