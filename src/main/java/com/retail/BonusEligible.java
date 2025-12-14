package com.retail;

/**
 * Інтерфейс для роботи з бонусами працівників
 */
public interface BonusEligible {
    // Константа за замовчуванням для бонусу
    double DEFAULT_BONUS = 0.0;
    
    // Отримати поточний бонус
    double getBonus();
    
    // Встановити значення бонусу
    void setBonus(double bonus);
}
