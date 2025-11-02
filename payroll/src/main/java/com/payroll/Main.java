package com.payroll;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Головний клас для демонстрації роботи програми
 */
public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new PayrollModule());
        Payroll payroll = injector.getInstance(Payroll.class);
        
        payroll.processPayroll(5000.0);
    }
}
