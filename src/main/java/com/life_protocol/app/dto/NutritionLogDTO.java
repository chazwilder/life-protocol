package com.life_protocol.app.dto;

import java.time.LocalDateTime;
import java.util.List;

public class NutritionLogDTO {
    private String id;
    private String userId;
    private LocalDateTime date;
    private List<MealEntryDTO> meals;
    private double totalCalories;
    private double totalProtein;
    private double totalCarbohydrates;
    private double totalFat;
    private double totalFiber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static class MealEntryDTO {
        private String id;
        private String foodId;
        private String mealType;
        private double quantity;
        private String quantityUnit;
        private LocalDateTime consumedAt;

        // Constructor, getters, and setters for MealEntryDTO
        public MealEntryDTO() {}

        public MealEntryDTO(String id, String foodId, String mealType, double quantity,
                            String quantityUnit, LocalDateTime consumedAt) {
            this.id = id;
            this.foodId = foodId;
            this.mealType = mealType;
            this.quantity = quantity;
            this.quantityUnit = quantityUnit;
            this.consumedAt = consumedAt;
        }

        // Getters and setters for all fields

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFoodId() {
            return foodId;
        }

        public void setFoodId(String foodId) {
            this.foodId = foodId;
        }

        public String getMealType() {
            return mealType;
        }

        public void setMealType(String mealType) {
            this.mealType = mealType;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getQuantityUnit() {
            return quantityUnit;
        }

        public void setQuantityUnit(String quantityUnit) {
            this.quantityUnit = quantityUnit;
        }

        public LocalDateTime getConsumedAt() {
            return consumedAt;
        }

        public void setConsumedAt(LocalDateTime consumedAt) {
            this.consumedAt = consumedAt;
        }
    }

    public NutritionLogDTO() {}

    public NutritionLogDTO(String id, String userId, LocalDateTime date, List<MealEntryDTO> meals,
                           double totalCalories, double totalProtein, double totalCarbohydrates,
                           double totalFat, double totalFiber, LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.meals = meals;
        this.totalCalories = totalCalories;
        this.totalProtein = totalProtein;
        this.totalCarbohydrates = totalCarbohydrates;
        this.totalFat = totalFat;
        this.totalFiber = totalFiber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and setters for all fields

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

    public List<MealEntryDTO> getMeals() {
        return meals;
    }

    public void setMeals(List<MealEntryDTO> meals) {
        this.meals = meals;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(double totalProtein) {
        this.totalProtein = totalProtein;
    }

    public double getTotalCarbohydrates() {
        return totalCarbohydrates;
    }

    public void setTotalCarbohydrates(double totalCarbohydrates) {
        this.totalCarbohydrates = totalCarbohydrates;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }

    public double getTotalFiber() {
        return totalFiber;
    }

    public void setTotalFiber(double totalFiber) {
        this.totalFiber = totalFiber;
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
}