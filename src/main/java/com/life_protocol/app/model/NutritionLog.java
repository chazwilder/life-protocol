package com.life_protocol.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "nutrition_logs")
public class NutritionLog {
    @Id
    private String id;

    private String userId;
    private LocalDateTime date;
    private List<MealEntry> meals;
    private double totalCalories;
    private double totalProtein;
    private double totalCarbohydrates;
    private double totalFat;
    private double totalFiber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Inner class to represent individual meal entries
    public static class MealEntry {
        private String foodId;
        private String mealType; // e.g., "Breakfast", "Lunch", "Dinner", "Snack"
        private double quantity;
        private String quantityUnit;
        private LocalDateTime consumedAt;

        // Constructors, getters, and setters for MealEntry
        // ...
    }

    // Constructors
    public NutritionLog() {}

    public NutritionLog(String userId, LocalDateTime date, List<MealEntry> meals) {
        this.userId = userId;
        this.date = date;
        this.meals = meals;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        calculateTotals();
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<MealEntry> getMeals() {
        return meals;
    }

    public void setMeals(List<MealEntry> meals) {
        this.meals = meals;
        calculateTotals();
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public double getTotalCarbohydrates() {
        return totalCarbohydrates;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public double getTotalFiber() {
        return totalFiber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Method to calculate totals based on meal entries
    private void calculateTotals() {
        // Implementation depends on how you store nutritional info for each food item
        // This is a placeholder for the actual calculation logic
    }

    @Override
    public String toString() {
        return "NutritionLog{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", date=" + date +
                ", meals=" + meals +
                ", totalCalories=" + totalCalories +
                ", totalProtein=" + totalProtein +
                ", totalCarbohydrates=" + totalCarbohydrates +
                ", totalFat=" + totalFat +
                ", totalFiber=" + totalFiber +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}