package com.life_protocol.app.controller;

import com.life_protocol.app.model.Goal;
import com.life_protocol.app.service.GoalService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/goals")
@Api(tags = "Goal Management", description = "Operations pertaining to goals in the Life Protocol application")
public class GoalController {

    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    @ApiOperation(value = "Create a new goal", response = Goal.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Goal successfully created"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<Goal> createGoal(@ApiParam(value = "Goal object to be created", required = true) @RequestBody Goal goal) {
        Goal createdGoal = goalService.createGoal(goal);
        return new ResponseEntity<>(createdGoal, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a goal by ID", response = Goal.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved goal"),
            @ApiResponse(code = 404, message = "Goal not found")
    })
    public ResponseEntity<Goal> getGoalById(@ApiParam(value = "ID of the goal to be retrieved", required = true) @PathVariable String id) {
        return goalService.getGoalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @ApiOperation(value = "Get goals by user ID", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of goals")
    public ResponseEntity<List<Goal>> getGoalsByUserId(@ApiParam(value = "ID of the user", required = true) @PathVariable String userId) {
        List<Goal> goals = goalService.getGoalsByUserId(userId);
        return ResponseEntity.ok(goals);
    }

    @GetMapping("/user/{userId}/active")
    @ApiOperation(value = "Get active goals by user ID", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of active goals")
    public ResponseEntity<List<Goal>> getActiveGoalsByUserId(@ApiParam(value = "ID of the user", required = true) @PathVariable String userId) {
        List<Goal> activeGoals = goalService.getActiveGoalsByUserId(userId);
        return ResponseEntity.ok(activeGoals);
    }

    @GetMapping("/user/{userId}/date-range")
    @ApiOperation(value = "Get goals by user ID and date range", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of goals")
    public ResponseEntity<List<Goal>> getGoalsByUserIdAndDateRange(
            @ApiParam(value = "ID of the user", required = true) @PathVariable String userId,
            @ApiParam(value = "Start date", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @ApiParam(value = "End date", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Goal> goals = goalService.getGoalsByUserIdAndDateRange(userId, startDate, endDate);
        return ResponseEntity.ok(goals);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a goal", response = Goal.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal successfully updated"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "Goal not found")
    })
    public ResponseEntity<Goal> updateGoal(
            @ApiParam(value = "ID of the goal to be updated", required = true) @PathVariable String id,
            @ApiParam(value = "Updated goal object", required = true) @RequestBody Goal goal) {
        if (!id.equals(goal.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Goal updatedGoal = goalService.updateGoal(goal);
        return ResponseEntity.ok(updatedGoal);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a goal")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Goal successfully deleted"),
            @ApiResponse(code = 404, message = "Goal not found")
    })
    public ResponseEntity<Void> deleteGoal(@ApiParam(value = "ID of the goal to be deleted", required = true) @PathVariable String id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}/completed")
    @ApiOperation(value = "Get completed goals by user ID", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of completed goals")
    public ResponseEntity<List<Goal>> getCompletedGoalsByUserId(@ApiParam(value = "ID of the user", required = true) @PathVariable String userId) {
        List<Goal> completedGoals = goalService.getCompletedGoalsByUserId(userId);
        return ResponseEntity.ok(completedGoals);
    }
}