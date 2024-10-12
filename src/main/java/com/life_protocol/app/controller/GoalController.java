// File path: src/main/java/com/life_protocol/app/controller/GoalController.java

package com.life_protocol.app.controller;

import com.life_protocol.app.model.Goal;
import com.life_protocol.app.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    public ResponseEntity<Goal> createGoal(@RequestBody Goal goal) {
        Goal createdGoal = goalService.createGoal(goal);
        return new ResponseEntity<>(createdGoal, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Goal> getGoalById(@PathVariable String id) {
        return goalService.getGoalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Goal>> getGoalsByUserId(@PathVariable String userId) {
        List<Goal> goals = goalService.getGoalsByUserId(userId);
        return ResponseEntity.ok(goals);
    }

    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<Goal>> getActiveGoalsByUserId(@PathVariable String userId) {
        List<Goal> activeGoals = goalService.getActiveGoalsByUserId(userId);
        return ResponseEntity.ok(activeGoals);
    }

    @GetMapping("/user/{userId}/date-range")
    public ResponseEntity<List<Goal>> getGoalsByUserIdAndDateRange(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Goal> goals = goalService.getGoalsByUserIdAndDateRange(userId, startDate, endDate);
        return ResponseEntity.ok(goals);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Goal> updateGoal(@PathVariable String id, @RequestBody Goal goal) {
        if (!id.equals(goal.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Goal updatedGoal = goalService.updateGoal(goal);
        return ResponseEntity.ok(updatedGoal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable String id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}/completed")
    public ResponseEntity<List<Goal>> getCompletedGoalsByUserId(@PathVariable String userId) {
        List<Goal> completedGoals = goalService.getCompletedGoalsByUserId(userId);
        return ResponseEntity.ok(completedGoals);
    }
}