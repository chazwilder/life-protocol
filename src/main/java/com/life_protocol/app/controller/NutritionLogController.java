package com.life_protocol.app.controller;

import com.life_protocol.app.model.NutritionLog;
import com.life_protocol.app.service.NutritionLogService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/nutrition-logs")
@Api(tags = "Nutrition Log Management", description = "Operations pertaining to nutrition logs in the Life Protocol application")
public class NutritionLogController {

    private final NutritionLogService nutritionLogService;

    @Autowired
    public NutritionLogController(NutritionLogService nutritionLogService) {
        this.nutritionLogService = nutritionLogService;
    }

    @PostMapping
    @ApiOperation(value = "Create a new nutrition log", response = NutritionLog.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Nutrition log successfully created"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<NutritionLog> createNutritionLog(@ApiParam(value = "Nutrition log object to be created", required = true) @RequestBody NutritionLog nutritionLog) {
        NutritionLog createdLog = nutritionLogService.createNutritionLog(nutritionLog);
        return new ResponseEntity<>(createdLog, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a nutrition log by ID", response = NutritionLog.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved nutrition log"),
            @ApiResponse(code = 404, message = "Nutrition log not found")
    })
    public ResponseEntity<NutritionLog> getNutritionLogById(@ApiParam(value = "ID of the nutrition log to be retrieved", required = true) @PathVariable String id) {
        return nutritionLogService.getNutritionLogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @ApiOperation(value = "Get nutrition logs by user ID", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of nutrition logs")
    public ResponseEntity<List<NutritionLog>> getNutritionLogsByUserId(@ApiParam(value = "ID of the user", required = true) @PathVariable String userId) {
        List<NutritionLog> logs = nutritionLogService.getNutritionLogsByUserId(userId);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/user/{userId}/date-range")
    @ApiOperation(value = "Get nutrition logs by user ID and date range", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of nutrition logs")
    public ResponseEntity<List<NutritionLog>> getNutritionLogsByUserIdAndDateRange(
            @ApiParam(value = "ID of the user", required = true) @PathVariable String userId,
            @ApiParam(value = "Start date and time", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @ApiParam(value = "End date and time", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<NutritionLog> logs = nutritionLogService.getNutritionLogsByUserIdAndDateRange(userId, start, end);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/user/{userId}/date")
    @ApiOperation(value = "Get nutrition log by user ID and date", response = NutritionLog.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved nutrition log"),
            @ApiResponse(code = 404, message = "Nutrition log not found")
    })
    public ResponseEntity<NutritionLog> getNutritionLogByUserIdAndDate(
            @ApiParam(value = "ID of the user", required = true) @PathVariable String userId,
            @ApiParam(value = "Date and time", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        NutritionLog log = nutritionLogService.getNutritionLogByUserIdAndDate(userId, date);
        return ResponseEntity.ok(log);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a nutrition log", response = NutritionLog.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Nutrition log successfully updated"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "Nutrition log not found")
    })
    public ResponseEntity<NutritionLog> updateNutritionLog(
            @ApiParam(value = "ID of the nutrition log to be updated", required = true) @PathVariable String id,
            @ApiParam(value = "Updated nutrition log object", required = true) @RequestBody NutritionLog nutritionLog) {
        if (!id.equals(nutritionLog.getId())) {
            return ResponseEntity.badRequest().build();
        }
        NutritionLog updatedLog = nutritionLogService.updateNutritionLog(nutritionLog);
        return ResponseEntity.ok(updatedLog);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a nutrition log")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Nutrition log successfully deleted"),
            @ApiResponse(code = 404, message = "Nutrition log not found")
    })
    public ResponseEntity<Void> deleteNutritionLog(@ApiParam(value = "ID of the nutrition log to be deleted", required = true) @PathVariable String id) {
        nutritionLogService.deleteNutritionLog(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{logId}/meals")
    @ApiOperation(value = "Add a meal to a nutrition log")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Meal successfully added to nutrition log"),
            @ApiResponse(code = 404, message = "Nutrition log not found")
    })
    public ResponseEntity<Void> addMealToNutritionLog(
            @ApiParam(value = "ID of the nutrition log", required = true) @PathVariable String logId,
            @ApiParam(value = "Meal entry to be added", required = true) @RequestBody NutritionLog.MealEntry meal) {
        nutritionLogService.addMealToNutritionLog(logId, meal);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{logId}/meals/{mealId}")
    @ApiOperation(value = "Remove a meal from a nutrition log")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Meal successfully removed from nutrition log"),
            @ApiResponse(code = 404, message = "Nutrition log or meal not found")
    })
    public ResponseEntity<Void> removeMealFromNutritionLog(
            @ApiParam(value = "ID of the nutrition log", required = true) @PathVariable String logId,
            @ApiParam(value = "ID of the meal to be removed", required = true) @PathVariable String mealId) {
        nutritionLogService.removeMealFromNutritionLog(logId, mealId);
        return ResponseEntity.noContent().build();
    }
}