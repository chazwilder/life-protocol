package com.life_protocol.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    public static class MealEntry {
        private String id;
        private String foodId;
        private String mealType;
        private double quantity;
        private String quantityUnit;
        private LocalDateTime consumedAt;

        public MealEntry() {
            this.id = UUID.randomUUID().toString();
        }

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

    public NutritionLog() {}

    public NutritionLog(String userId, LocalDateTime date, List<MealEntry> meals) {
        this.userId = userId;
        this.date = date;
        this.meals = meals;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        calculateTotals();
    }

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

    public void calculateTotals() {
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