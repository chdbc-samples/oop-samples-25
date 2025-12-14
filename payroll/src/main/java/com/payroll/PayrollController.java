package com.payroll;

import java.util.List;

import com.google.inject.Inject;

/**
 * Контролер для управління обробкою зарплати.
 * Реалізує патерн MVC як проміжну ланку між View та Model.
 */
public class PayrollController {
    private final Payroll payroll;

    /**
     * Конструктор з впровадженням залежності Payroll (Model).
     *
     * @param payroll модель для обробки зарплати
     */
    @Inject
    public PayrollController(Payroll payroll) {
        this.payroll = payroll;
    }

    /**
     * Отримує всі квитанції з бази даних.
     *
     * @return список всіх квитанцій
     */
    public List<Paycheck> getAllPaychecks() {
        return payroll.getAllPaychecks();
    }
}
