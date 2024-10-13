package com.life_protocol.app.controller;

import com.life_protocol.app.model.Workout;
import com.life_protocol.app.service.WorkoutService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@Api(tags = "Workout Management", description = "Operations pertaining to workouts in the Life Protocol application")
public class WorkoutController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    @ApiOperation(value = "Create a new workout", response = Workout.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Workout successfully created"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<Workout> createWorkout(@ApiParam(value = "Workout object to be created", required = true) @RequestBody Workout workout) {
        Workout createdWorkout = workoutService.createWorkout(workout);
        return new ResponseEntity<>(createdWorkout, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a workout by ID", response = Workout.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved workout"),
            @ApiResponse(code = 404, message = "Workout not found")
    })
    public ResponseEntity<Workout> getWorkoutById(@ApiParam(value = "ID of the workout to be retrieved", required = true) @PathVariable String id) {
        return workoutService.getWorkoutById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @ApiOperation(value = "Get workouts by user ID", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of workouts")
    public ResponseEntity<List<Workout>> getWorkoutsByUserId(@ApiParam(value = "ID of the user", required = true) @PathVariable String userId) {
        List<Workout> workouts = workoutService.getWorkoutsByUserId(userId);
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/user/{userId}/date-range")
    @ApiOperation(value = "Get workouts by user ID and date range", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of workouts")
    public ResponseEntity<List<Workout>> getWorkoutsByUserIdAndDateRange(
            @ApiParam(value = "ID of the user", required = true) @PathVariable String userId,
            @ApiParam(value = "Start date and time", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @ApiParam(value = "End date and time", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<Workout> workouts = workoutService.getWorkoutsByUserIdAndDateRange(userId, start, end);
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/user/{userId}/recent")
    @ApiOperation(value = "Get recent workouts by user ID", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of recent workouts")
    public ResponseEntity<List<Workout>> getRecentWorkoutsByUserId(
            @ApiParam(value = "ID of the user", required = true) @PathVariable String userId,
            @ApiParam(value = "Limit of workouts to retrieve", defaultValue = "10") @RequestParam(defaultValue = "10") int limit) {
        List<Workout> recentWorkouts = workoutService.getRecentWorkoutsByUserId(userId, limit);
        return ResponseEntity.ok(recentWorkouts);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a workout", response = Workout.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Workout successfully updated"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "Workout not found")
    })
    public ResponseEntity<Workout> updateWorkout(
            @ApiParam(value = "ID of the workout to be updated", required = true) @PathVariable String id,
            @ApiParam(value = "Updated workout object", required = true) @RequestBody Workout workout) {
        if (!id.equals(workout.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Workout updatedWorkout = workoutService.updateWorkout(workout);
        return ResponseEntity.ok(updatedWorkout);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a workout")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Workout successfully deleted"),
            @ApiResponse(code = 404, message = "Workout not found")
    })
    public ResponseEntity<Void> deleteWorkout(@ApiParam(value = "ID of the workout to be deleted", required = true) @PathVariable String id) {
        workoutService.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{workoutId}/exercises/{exerciseId}")
    @ApiOperation(value = "Add an exercise to a workout")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Exercise successfully added to workout"),
            @ApiResponse(code = 404, message = "Workout or exercise not found")
    })
    public ResponseEntity<Void> addExerciseToWorkout(
            @ApiParam(value = "ID of the workout", required = true) @PathVariable String workoutId,
            @ApiParam(value = "ID of the exercise to be added", required = true) @PathVariable String exerciseId) {
        workoutService.addExerciseToWorkout(workoutId, exerciseId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{workoutId}/exercises/{exerciseId}")
    @ApiOperation(value = "Remove an exercise from a workout")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Exercise successfully removed from workout"),
            @ApiResponse(code = 404, message = "Workout or exercise not found")
    })
    public ResponseEntity<Void> removeExerciseFromWorkout(
            @ApiParam(value = "ID of the workout", required = true) @PathVariable String workoutId,
            @ApiParam(value = "ID of the exercise to be removed", required = true) @PathVariable String exerciseId) {
        workoutService.removeExerciseFromWorkout(workoutId, exerciseId);
        return ResponseEntity.noContent().build();
    }
}