package com.life_protocol.app.controller;

import com.life_protocol.app.model.WorkoutRoutine;
import com.life_protocol.app.service.WorkoutRoutineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-routines")
@Tag(name = "Workout Routine Management", description = "Operations pertaining to workout routines in the Life Protocol application")
public class WorkoutRoutineController {

    private final WorkoutRoutineService workoutRoutineService;

    @Autowired
    public WorkoutRoutineController(WorkoutRoutineService workoutRoutineService) {
        this.workoutRoutineService = workoutRoutineService;
    }

    @PostMapping
    @Operation(summary = "Create a new workout routine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Workout routine successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<WorkoutRoutine> createWorkoutRoutine(@Parameter(description = "Workout routine object to be created", required = true) @RequestBody WorkoutRoutine workoutRoutine) {
        WorkoutRoutine createdRoutine = workoutRoutineService.createWorkoutRoutine(workoutRoutine);
        return new ResponseEntity<>(createdRoutine, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a workout routine by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved workout routine"),
            @ApiResponse(responseCode = "404", description = "Workout routine not found")
    })
    public ResponseEntity<WorkoutRoutine> getWorkoutRoutineById(@Parameter(description = "ID of the workout routine to be retrieved", required = true) @PathVariable String id) {
        return workoutRoutineService.getWorkoutRoutineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get workout routines by user ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of workout routines")
    public ResponseEntity<List<WorkoutRoutine>> getWorkoutRoutinesByUserId(@Parameter(description = "ID of the user", required = true) @PathVariable String userId) {
        List<WorkoutRoutine> routines = workoutRoutineService.getWorkoutRoutinesByUserId(userId);
        return ResponseEntity.ok(routines);
    }

    @GetMapping("/user/{userId}/difficulty/{difficultyLevel}")
    @Operation(summary = "Get workout routines by user ID and difficulty level")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of workout routines")
    public ResponseEntity<List<WorkoutRoutine>> getWorkoutRoutinesByDifficultyLevel(
            @Parameter(description = "ID of the user", required = true) @PathVariable String userId,
            @Parameter(description = "Difficulty level", required = true) @PathVariable String difficultyLevel) {
        List<WorkoutRoutine> routines = workoutRoutineService.getWorkoutRoutinesByDifficultyLevel(userId, difficultyLevel);
        return ResponseEntity.ok(routines);
    }

    @GetMapping("/search")
    @Operation(summary = "Search workout routines by name")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of workout routines")
    public ResponseEntity<List<WorkoutRoutine>> searchWorkoutRoutines(@Parameter(description = "Name to search for", required = true) @RequestParam String name) {
        List<WorkoutRoutine> routines = workoutRoutineService.searchWorkoutRoutines(name);
        return ResponseEntity.ok(routines);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a workout routine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workout routine successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Workout routine not found")
    })
    public ResponseEntity<WorkoutRoutine> updateWorkoutRoutine(
            @Parameter(description = "ID of the workout routine to be updated", required = true) @PathVariable String id,
            @Parameter(description = "Updated workout routine object", required = true) @RequestBody WorkoutRoutine workoutRoutine) {
        if (!id.equals(workoutRoutine.getId())) {
            return ResponseEntity.badRequest().build();
        }
        WorkoutRoutine updatedRoutine = workoutRoutineService.updateWorkoutRoutine(workoutRoutine);
        return ResponseEntity.ok(updatedRoutine);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a workout routine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Workout routine successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Workout routine not found")
    })
    public ResponseEntity<Void> deleteWorkoutRoutine(@Parameter(description = "ID of the workout routine to be deleted", required = true) @PathVariable String id) {
        workoutRoutineService.deleteWorkoutRoutine(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{routineId}/exercises")
    @Operation(summary = "Add an exercise to a workout routine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exercise successfully added to workout routine"),
            @ApiResponse(responseCode = "404", description = "Workout routine not found")
    })
    public ResponseEntity<Void> addExerciseToRoutine(
            @Parameter(description = "ID of the workout routine", required = true) @PathVariable String routineId,
            @Parameter(description = "Exercise to be added", required = true) @RequestBody WorkoutRoutine.WorkoutExercise exercise) {
        workoutRoutineService.addExerciseToRoutine(routineId, exercise);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{routineId}/exercises/{exerciseId}")
    @Operation(summary = "Remove an exercise from a workout routine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exercise successfully removed from workout routine"),
            @ApiResponse(responseCode = "404", description = "Workout routine or exercise not found")
    })
    public ResponseEntity<Void> removeExerciseFromRoutine(
            @Parameter(description = "ID of the workout routine", required = true) @PathVariable String routineId,
            @Parameter(description = "ID of the exercise to be removed", required = true) @PathVariable String exerciseId) {
        workoutRoutineService.removeExerciseFromRoutine(routineId, exerciseId);
        return ResponseEntity.noContent().build();
    }
}