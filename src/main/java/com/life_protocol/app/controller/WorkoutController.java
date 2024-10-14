package com.life_protocol.app.controller;

import com.life_protocol.app.model.Workout;
import com.life_protocol.app.service.WorkoutService;
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
@RequestMapping("/api/workouts")
@Tag(name = "Workout Management", description = "Operations pertaining to workouts in the Life Protocol application")
public class WorkoutController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    @Operation(summary = "Create a new workout")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Workout successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Workout> createWorkout(@Parameter(description = "Workout object to be created", required = true) @RequestBody Workout workout) {
        Workout createdWorkout = workoutService.createWorkout(workout);
        return new ResponseEntity<>(createdWorkout, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a workout by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved workout"),
            @ApiResponse(responseCode = "404", description = "Workout not found")
    })
    public ResponseEntity<Workout> getWorkoutById(@Parameter(description = "ID of the workout to be retrieved", required = true) @PathVariable String id) {
        return workoutService.getWorkoutById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get workouts by user ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of workouts")
    public ResponseEntity<List<Workout>> getWorkoutsByUserId(@Parameter(description = "ID of the user", required = true) @PathVariable String userId) {
        List<Workout> workouts = workoutService.getWorkoutsByUserId(userId);
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/user/{userId}/date-range")
    @Operation(summary = "Get workouts by user ID and date range")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of workouts")
    public ResponseEntity<List<Workout>> getWorkoutsByUserIdAndDateRange(
            @Parameter(description = "ID of the user", required = true) @PathVariable String userId,
            @Parameter(description = "Start date and time", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @Parameter(description = "End date and time", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<Workout> workouts = workoutService.getWorkoutsByUserIdAndDateRange(userId, start, end);
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/user/{userId}/recent")
    @Operation(summary = "Get recent workouts by user ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of recent workouts")
    public ResponseEntity<List<Workout>> getRecentWorkoutsByUserId(
            @Parameter(description = "ID of the user", required = true) @PathVariable String userId,
            @Parameter(description = "Limit of workouts to retrieve", required = false) @RequestParam(defaultValue = "10") int limit) {
        List<Workout> recentWorkouts = workoutService.getRecentWorkoutsByUserId(userId, limit);
        return ResponseEntity.ok(recentWorkouts);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a workout")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workout successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Workout not found")
    })
    public ResponseEntity<Workout> updateWorkout(
            @Parameter(description = "ID of the workout to be updated", required = true) @PathVariable String id,
            @Parameter(description = "Updated workout object", required = true) @RequestBody Workout workout) {
        if (!id.equals(workout.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Workout updatedWorkout = workoutService.updateWorkout(workout);
        return ResponseEntity.ok(updatedWorkout);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a workout")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Workout successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Workout not found")
    })
    public ResponseEntity<Void> deleteWorkout(@Parameter(description = "ID of the workout to be deleted", required = true) @PathVariable String id) {
        workoutService.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{workoutId}/exercises/{exerciseId}")
    @Operation(summary = "Add an exercise to a workout")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exercise successfully added to workout"),
            @ApiResponse(responseCode = "404", description = "Workout or exercise not found")
    })
    public ResponseEntity<Void> addExerciseToWorkout(
            @Parameter(description = "ID of the workout", required = true) @PathVariable String workoutId,
            @Parameter(description = "ID of the exercise to be added", required = true) @PathVariable String exerciseId) {
        workoutService.addExerciseToWorkout(workoutId, exerciseId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{workoutId}/exercises/{exerciseId}")
    @Operation(summary = "Remove an exercise from a workout")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exercise successfully removed from workout"),
            @ApiResponse(responseCode = "404", description = "Workout or exercise not found")
    })
    public ResponseEntity<Void> removeExerciseFromWorkout(
            @Parameter(description = "ID of the workout", required = true) @PathVariable String workoutId,
            @Parameter(description = "ID of the exercise to be removed", required = true) @PathVariable String exerciseId) {
        workoutService.removeExerciseFromWorkout(workoutId, exerciseId);
        return ResponseEntity.noContent().build();
    }
}