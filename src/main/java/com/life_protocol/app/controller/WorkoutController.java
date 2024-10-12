package com.life_protocol.app.controller;

import com.life_protocol.app.model.Workout;
import com.life_protocol.app.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout) {
        Workout createdWorkout = workoutService.createWorkout(workout);
        return new ResponseEntity<>(createdWorkout, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable String id) {
        return workoutService.getWorkoutById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Workout>> getWorkoutsByUserId(@PathVariable String userId) {
        List<Workout> workouts = workoutService.getWorkoutsByUserId(userId);
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/user/{userId}/date-range")
    public ResponseEntity<List<Workout>> getWorkoutsByUserIdAndDateRange(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<Workout> workouts = workoutService.getWorkoutsByUserIdAndDateRange(userId, start, end);
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/user/{userId}/recent")
    public ResponseEntity<List<Workout>> getRecentWorkoutsByUserId(
            @PathVariable String userId,
            @RequestParam(defaultValue = "10") int limit) {
        List<Workout> recentWorkouts = workoutService.getRecentWorkoutsByUserId(userId, limit);
        return ResponseEntity.ok(recentWorkouts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable String id, @RequestBody Workout workout) {
        if (!id.equals(workout.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Workout updatedWorkout = workoutService.updateWorkout(workout);
        return ResponseEntity.ok(updatedWorkout);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable String id) {
        workoutService.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{workoutId}/exercises/{exerciseId}")
    public ResponseEntity<Void> addExerciseToWorkout(@PathVariable String workoutId, @PathVariable String exerciseId) {
        workoutService.addExerciseToWorkout(workoutId, exerciseId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{workoutId}/exercises/{exerciseId}")
    public ResponseEntity<Void> removeExerciseFromWorkout(@PathVariable String workoutId, @PathVariable String exerciseId) {
        workoutService.removeExerciseFromWorkout(workoutId, exerciseId);
        return ResponseEntity.noContent().build();
    }
}