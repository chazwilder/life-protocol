package com.life_protocol.app.service;

import com.life_protocol.app.model.NutritionLog;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NutritionLogService {
    NutritionLog createNutritionLog(NutritionLog nutritionLog);
    Optional<NutritionLog> getNutritionLogById(String id);
    List<NutritionLog> getNutritionLogsByUserId(String userId);
    List<NutritionLog> getNutritionLogsByUserIdAndDateRange(String userId, LocalDateTime start, LocalDateTime end);
    NutritionLog getNutritionLogByUserIdAndDate(String userId, LocalDateTime date);
    NutritionLog updateNutritionLog(NutritionLog nutritionLog);
    void deleteNutritionLog(String id);
    void addMealToNutritionLog(String logId, NutritionLog.MealEntry meal);
    void removeMealFromNutritionLog(String logId, String mealId);
}