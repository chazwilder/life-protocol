package com.life_protocol.app.controller;

import com.life_protocol.app.model.NutritionLog;
import com.life_protocol.app.service.NutritionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/nutrition-logs")
public class NutritionLogController {

    private final NutritionLogService nutritionLogService;

    @Autowired
    public NutritionLogController(NutritionLogService nutritionLogService) {
        this.nutritionLogService = nutritionLogService;
    }

    @PostMapping
    public ResponseEntity<NutritionLog> createNutritionLog(@RequestBody NutritionLog nutritionLog) {
        NutritionLog createdLog = nutritionLogService.createNutritionLog(nutritionLog);
        return new ResponseEntity<>(createdLog, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NutritionLog> getNutritionLogById(@PathVariable String id) {
        return nutritionLogService.getNutritionLogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NutritionLog>> getNutritionLogsByUserId(@PathVariable String userId) {
        List<NutritionLog> logs = nutritionLogService.getNutritionLogsByUserId(userId);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/user/{userId}/date-range")
    public ResponseEntity<List<NutritionLog>> getNutritionLogsByUserIdAndDateRange(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<NutritionLog> logs = nutritionLogService.getNutritionLogsByUserIdAndDateRange(userId, start, end);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/user/{userId}/date")
    public ResponseEntity<NutritionLog> getNutritionLogByUserIdAndDate(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        NutritionLog log = nutritionLogService.getNutritionLogByUserIdAndDate(userId, date);
        return ResponseEntity.ok(log);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NutritionLog> updateNutritionLog(@PathVariable String id, @RequestBody NutritionLog nutritionLog) {
        if (!id.equals(nutritionLog.getId())) {
            return ResponseEntity.badRequest().build();
        }
        NutritionLog updatedLog = nutritionLogService.updateNutritionLog(nutritionLog);
        return ResponseEntity.ok(updatedLog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNutritionLog(@PathVariable String id) {
        nutritionLogService.deleteNutritionLog(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{logId}/meals")
    public ResponseEntity<Void> addMealToNutritionLog(
            @PathVariable String logId,
            @RequestBody NutritionLog.MealEntry meal) {
        nutritionLogService.addMealToNutritionLog(logId, meal);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{logId}/meals/{mealId}")
    public ResponseEntity<Void> removeMealFromNutritionLog(
            @PathVariable String logId,
            @PathVariable String mealId) {
        nutritionLogService.removeMealFromNutritionLog(logId, mealId);
        return ResponseEntity.noContent().build();
    }
}