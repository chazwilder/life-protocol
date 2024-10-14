package com.life_protocol.app.controller;

import com.life_protocol.app.model.Goal;
import com.life_protocol.app.service.GoalService;
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

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/goals")
@Tag(name = "Goal Management", description = "Operations pertaining to goals in the Life Protocol application")
public class GoalController {

    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    @Operation(summary = "Create a new goal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Goal successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Goal> createGoal(@Parameter(description = "Goal object to be created", required = true) @RequestBody Goal goal) {
        Goal createdGoal = goalService.createGoal(goal);
        return new ResponseEntity<>(createdGoal, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a goal by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved goal"),
            @ApiResponse(responseCode = "404", description = "Goal not found")
    })
    public ResponseEntity<Goal> getGoalById(@Parameter(description = "ID of the goal to be retrieved", required = true) @PathVariable String id) {
        return goalService.getGoalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get goals by user ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of goals")
    public ResponseEntity<List<Goal>> getGoalsByUserId(@Parameter(description = "ID of the user", required = true) @PathVariable String userId) {
        List<Goal> goals = goalService.getGoalsByUserId(userId);
        return ResponseEntity.ok(goals);
    }

    @GetMapping("/user/{userId}/active")
    @Operation(summary = "Get active goals by user ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of active goals")
    public ResponseEntity<List<Goal>> getActiveGoalsByUserId(@Parameter(description = "ID of the user", required = true) @PathVariable String userId) {
        List<Goal> activeGoals = goalService.getActiveGoalsByUserId(userId);
        return ResponseEntity.ok(activeGoals);
    }

    @GetMapping("/user/{userId}/date-range")
    @Operation(summary = "Get goals by user ID and date range")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of goals")
    public ResponseEntity<List<Goal>> getGoalsByUserIdAndDateRange(
            @Parameter(description = "ID of the user", required = true) @PathVariable String userId,
            @Parameter(description = "Start date", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "End date", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Goal> goals = goalService.getGoalsByUserIdAndDateRange(userId, startDate, endDate);
        return ResponseEntity.ok(goals);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a goal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Goal successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Goal not found")
    })
    public ResponseEntity<Goal> updateGoal(
            @Parameter(description = "ID of the goal to be updated", required = true) @PathVariable String id,
            @Parameter(description = "Updated goal object", required = true) @RequestBody Goal goal) {
        if (!id.equals(goal.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Goal updatedGoal = goalService.updateGoal(goal);
        return ResponseEntity.ok(updatedGoal);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a goal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Goal successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Goal not found")
    })
    public ResponseEntity<Void> deleteGoal(@Parameter(description = "ID of the goal to be deleted", required = true) @PathVariable String id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}/completed")
    @Operation(summary = "Get completed goals by user ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of completed goals")
    public ResponseEntity<List<Goal>> getCompletedGoalsByUserId(@Parameter(description = "ID of the user", required = true) @PathVariable String userId) {
        List<Goal> completedGoals = goalService.getCompletedGoalsByUserId(userId);
        return ResponseEntity.ok(completedGoals);
    }
}