package com.life_protocol.app.controller;

import com.life_protocol.app.model.NutritionLog;
import com.life_protocol.app.service.NutritionLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/nutrition-logs")
@Tag(name = "Nutrition Log Management", description = "Operations pertaining to nutrition logs in the Life Protocol application")
public class NutritionLogController {

    private final NutritionLogService nutritionLogService;

    @Autowired
    public NutritionLogController(NutritionLogService nutritionLogService) {
        this.nutritionLogService = nutritionLogService;
    }

    @PostMapping
    @Operation(summary = "Create a new nutrition log")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Nutrition log successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<NutritionLog> createNutritionLog(@Parameter(description = "Nutrition log object to be created", required = true) @RequestBody NutritionLog nutritionLog) {
        NutritionLog createdLog = nutritionLogService.createNutritionLog(nutritionLog);
        return new ResponseEntity<>(createdLog, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a nutrition log by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved nutrition log"),
            @ApiResponse(responseCode = "404", description = "Nutrition log not found")
    })
    public ResponseEntity<NutritionLog> getNutritionLogById(@Parameter(description = "ID of the nutrition log to be retrieved", required = true) @PathVariable String id) {
        return nutritionLogService.getNutritionLogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get nutrition logs by user ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of nutrition logs")
    public ResponseEntity<List<NutritionLog>> getNutritionLogsByUserId(@Parameter(description = "ID of the user", required = true) @PathVariable String userId) {
        List<NutritionLog> logs = nutritionLogService.getNutritionLogsByUserId(userId);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/user/{userId}/date-range")
    @Operation(summary = "Get nutrition logs by user ID and date range")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of nutrition logs")
    public ResponseEntity<List<NutritionLog>> getNutritionLogsByUserIdAndDateRange(
            @Parameter(description = "ID of the user", required = true) @PathVariable String userId,
            @Parameter(description = "Start date and time", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @Parameter(description = "End date and time", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<NutritionLog> logs = nutritionLogService.getNutritionLogsByUserIdAndDateRange(userId, start, end);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/user/{userId}/date")
    @Operation(summary = "Get nutrition log by user ID and date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved nutrition log"),
            @ApiResponse(responseCode = "404", description = "Nutrition log not found")
    })
    public ResponseEntity<NutritionLog> getNutritionLogByUserIdAndDate(
            @Parameter(description = "ID of the user", required = true) @PathVariable String userId,
            @Parameter(description = "Date and time", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        NutritionLog log = nutritionLogService.getNutritionLogByUserIdAndDate(userId, date);
        return ResponseEntity.ok(log);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a nutrition log")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nutrition log successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Nutrition log not found")
    })
    public ResponseEntity<NutritionLog> updateNutritionLog(
            @Parameter(description = "ID of the nutrition log to be updated", required = true) @PathVariable String id,
            @Parameter(description = "Updated nutrition log object", required = true) @RequestBody NutritionLog nutritionLog) {
        if (!id.equals(nutritionLog.getId())) {
            return ResponseEntity.badRequest().build();
        }
        NutritionLog updatedLog = nutritionLogService.updateNutritionLog(nutritionLog);
        return ResponseEntity.ok(updatedLog);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a nutrition log")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nutrition log successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Nutrition log not found")
    })
    public ResponseEntity<Void> deleteNutritionLog(@Parameter(description = "ID of the nutrition log to be deleted", required = true) @PathVariable String id) {
        nutritionLogService.deleteNutritionLog(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{logId}/meals")
    @Operation(summary = "Add a meal to a nutrition log")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Meal successfully added to nutrition log"),
            @ApiResponse(responseCode = "404", description = "Nutrition log not found")
    })
    public ResponseEntity<Void> addMealToNutritionLog(
            @Parameter(description = "ID of the nutrition log", required = true) @PathVariable String logId,
            @Parameter(description = "Meal entry to be added", required = true) @RequestBody NutritionLog.MealEntry meal) {
        nutritionLogService.addMealToNutritionLog(logId, meal);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{logId}/meals/{mealId}")
    @Operation(summary = "Remove a meal from a nutrition log")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Meal successfully removed from nutrition log"),
            @ApiResponse(responseCode = "404", description = "Nutrition log or meal not found")
    })
    public ResponseEntity<Void> removeMealFromNutritionLog(
            @Parameter(description = "ID of the nutrition log", required = true) @PathVariable String logId,
            @Parameter(description = "ID of the meal to be removed", required = true) @PathVariable String mealId) {
        nutritionLogService.removeMealFromNutritionLog(logId, mealId);
        return ResponseEntity.noContent().build();
    }
}