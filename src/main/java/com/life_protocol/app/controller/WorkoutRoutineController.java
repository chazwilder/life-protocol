package com.life_protocol.app.controller;

import com.life_protocol.app.model.WorkoutRoutine;
import com.life_protocol.app.service.WorkoutRoutineService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-routines")
@Api(tags = "Workout Routine Management", description = "Operations pertaining to workout routines in the Life Protocol application")
public class WorkoutRoutineController {

    private final WorkoutRoutineService workoutRoutineService;

    @Autowired
    public WorkoutRoutineController(WorkoutRoutineService workoutRoutineService) {
        this.workoutRoutineService = workoutRoutineService;
    }

    @PostMapping
    @ApiOperation(value = "Create a new workout routine", response = WorkoutRoutine.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Workout routine successfully created"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<WorkoutRoutine> createWorkoutRoutine(@ApiParam(value = "Workout routine object to be created", required = true) @RequestBody WorkoutRoutine workoutRoutine) {
        WorkoutRoutine createdRoutine = workoutRoutineService.createWorkoutRoutine(workoutRoutine);
        return new ResponseEntity<>(createdRoutine, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a workout routine by ID", response = WorkoutRoutine.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved workout routine"),
            @ApiResponse(code = 404, message = "Workout routine not found")
    })
    public ResponseEntity<WorkoutRoutine> getWorkoutRoutineById(@ApiParam(value = "ID of the workout routine to be retrieved", required = true) @PathVariable String id) {
        return workoutRoutineService.getWorkoutRoutineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @ApiOperation(value = "Get workout routines by user ID", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of workout routines")
    public ResponseEntity<List<WorkoutRoutine>> getWorkoutRoutinesByUserId(@ApiParam(value = "ID of the user", required = true) @PathVariable String userId) {
        List<WorkoutRoutine> routines = workoutRoutineService.getWorkoutRoutinesByUserId(userId);
        return ResponseEntity.ok(routines);
    }

    @GetMapping("/user/{userId}/difficulty/{difficultyLevel}")
    @ApiOperation(value = "Get workout routines by user ID and difficulty level", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of workout routines")
    public ResponseEntity<List<WorkoutRoutine>> getWorkoutRoutinesByDifficultyLevel(
            @ApiParam(value = "ID of the user", required = true) @PathVariable String userId,
            @ApiParam(value = "Difficulty level", required = true) @PathVariable String difficultyLevel) {
        List<WorkoutRoutine> routines = workoutRoutineService.getWorkoutRoutinesByDifficultyLevel(userId, difficultyLevel);
        return ResponseEntity.ok(routines);
    }

    @GetMapping("/search")
    @ApiOperation(value = "Search workout routines by name", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of workout routines")
    public ResponseEntity<List<WorkoutRoutine>> searchWorkoutRoutines(@ApiParam(value = "Name to search for", required = true) @RequestParam String name) {
        List<WorkoutRoutine> routines = workoutRoutineService.searchWorkoutRoutines(name);
        return ResponseEntity.ok(routines);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a workout routine", response = WorkoutRoutine.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Workout routine successfully updated"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "Workout routine not found")
    })
    public ResponseEntity<WorkoutRoutine> updateWorkoutRoutine(
            @ApiParam(value = "ID of the workout routine to be updated", required = true) @PathVariable String id,
            @ApiParam(value = "Updated workout routine object", required = true) @RequestBody WorkoutRoutine workoutRoutine) {
        if (!id.equals(workoutRoutine.getId())) {
            return ResponseEntity.badRequest().build();
        }
        WorkoutRoutine updatedRoutine = workoutRoutineService.updateWorkoutRoutine(workoutRoutine);
        return ResponseEntity.ok(updatedRoutine);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a workout routine")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Workout routine successfully deleted"),
            @ApiResponse(code = 404, message = "Workout routine not found")
    })
    public ResponseEntity<Void> deleteWorkoutRoutine(@ApiParam(value = "ID of the workout routine to be deleted", required = true) @PathVariable String id) {
        workoutRoutineService.deleteWorkoutRoutine(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{routineId}/exercises")
    @ApiOperation(value = "Add an exercise to a workout routine")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Exercise successfully added to workout routine"),
            @ApiResponse(code = 404, message = "Workout routine not found")
    })
    public ResponseEntity<Void> addExerciseToRoutine(
            @ApiParam(value = "ID of the workout routine", required = true) @PathVariable String routineId,
            @ApiParam(value = "Exercise to be added", required = true) @RequestBody WorkoutRoutine.WorkoutExercise exercise) {
        workoutRoutineService.addExerciseToRoutine(routineId, exercise);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{routineId}/exercises/{exerciseId}")
    @ApiOperation(value = "Remove an exercise from a workout routine")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Exercise successfully removed from workout routine"),
            @ApiResponse(code = 404, message = "Workout routine or exercise not found")
    })
    public ResponseEntity<Void> removeExerciseFromRoutine(
            @ApiParam(value = "ID of the workout routine", required = true) @PathVariable String routineId,
            @ApiParam(value = "ID of the exercise to be removed", required = true) @PathVariable String exerciseId) {
        workoutRoutineService.removeExerciseFromRoutine(routineId, exerciseId);
        return ResponseEntity.noContent().build();
    }
}