package com.life_protocol.app.service;

import com.life_protocol.app.model.NutritionLog;
import com.life_protocol.app.repository.NutritionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NutritionLogServiceImpl implements NutritionLogService {

    private final NutritionLogRepository nutritionLogRepository;

    @Autowired
    public NutritionLogServiceImpl(NutritionLogRepository nutritionLogRepository) {
        this.nutritionLogRepository = nutritionLogRepository;
    }

    @Override
    public NutritionLog createNutritionLog(NutritionLog nutritionLog) {
        nutritionLog.setCreatedAt(LocalDateTime.now());
        nutritionLog.setUpdatedAt(LocalDateTime.now());
        return nutritionLogRepository.save(nutritionLog);
    }

    @Override
    public Optional<NutritionLog> getNutritionLogById(String id) {
        return nutritionLogRepository.findById(id);
    }

    @Override
    public List<NutritionLog> getNutritionLogsByUserId(String userId) {
        return nutritionLogRepository.findByUserId(userId);
    }

    @Override
    public List<NutritionLog> getNutritionLogsByUserIdAndDateRange(String userId, LocalDateTime start, LocalDateTime end) {
        return nutritionLogRepository.findByUserIdAndDateBetween(userId, start, end);
    }

    @Override
    public NutritionLog getNutritionLogByUserIdAndDate(String userId, LocalDateTime date) {
        return nutritionLogRepository.findByUserIdAndDate(userId, date);
    }

    @Override
    public NutritionLog updateNutritionLog(NutritionLog nutritionLog) {
        nutritionLog.setUpdatedAt(LocalDateTime.now());
        return nutritionLogRepository.save(nutritionLog);
    }

    @Override
    public void deleteNutritionLog(String id) {
        nutritionLogRepository.deleteById(id);
    }

    @Override
    public void addMealToNutritionLog(String logId, NutritionLog.MealEntry meal) {
        nutritionLogRepository.findById(logId).ifPresent(log -> {
            log.getMeals().add(meal);
            log.setUpdatedAt(LocalDateTime.now());
            log.calculateTotals();
            nutritionLogRepository.save(log);
        });
    }

    @Override
    public void removeMealFromNutritionLog(String logId, String mealId) {
        nutritionLogRepository.findById(logId).ifPresent(log -> {
            log.getMeals().removeIf(meal -> meal.getId().equals(mealId));
            log.setUpdatedAt(LocalDateTime.now());
            log.calculateTotals();
            nutritionLogRepository.save(log);
        });
    }
}