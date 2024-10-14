package com.life_protocol.app.controller;

import com.life_protocol.app.model.Exercise;
import com.life_protocol.app.service.ExerciseService;
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
@RequestMapping("/api/exercises")
@Tag(name = "Exercise Management", description = "Operations pertaining to exercises in the Life Protocol application")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping
    @Operation(summary = "Create a new exercise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Exercise successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Exercise> createExercise(@Parameter(description = "Exercise object to be created", required = true) @RequestBody Exercise exercise) {
        Exercise createdExercise = exerciseService.createExercise(exercise);
        return new ResponseEntity<>(createdExercise, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an exercise by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved exercise"),
            @ApiResponse(responseCode = "404", description = "Exercise not found")
    })
    public ResponseEntity<Exercise> getExerciseById(@Parameter(description = "ID of the exercise to be retrieved", required = true) @PathVariable String id) {
        return exerciseService.getExerciseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all exercises")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of exercises")
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseService.getAllExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get exercises by category")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of exercises")
    public ResponseEntity<List<Exercise>> getExercisesByCategory(@Parameter(description = "Category of exercises to retrieve", required = true) @PathVariable String category) {
        List<Exercise> exercises = exerciseService.getExercisesByCategory(category);
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/muscle-group/{muscleGroup}")
    @Operation(summary = "Get exercises by muscle group")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of exercises")
    public ResponseEntity<List<Exercise>> getExercisesByMuscleGroup(@Parameter(description = "Muscle group of exercises to retrieve", required = true) @PathVariable String muscleGroup) {
        List<Exercise> exercises = exerciseService.getExercisesByMuscleGroup(muscleGroup);
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/search")
    @Operation(summary = "Search exercises by name")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of exercises")
    public ResponseEntity<List<Exercise>> searchExercises(@Parameter(description = "Name to search for", required = true) @RequestParam String name) {
        List<Exercise> exercises = exerciseService.searchExercises(name);
        return ResponseEntity.ok(exercises);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an exercise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercise successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Exercise not found")
    })
    public ResponseEntity<Exercise> updateExercise(
            @Parameter(description = "ID of the exercise to be updated", required = true) @PathVariable String id,
            @Parameter(description = "Updated exercise object", required = true) @RequestBody Exercise exercise) {
        if (!id.equals(exercise.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Exercise updatedExercise = exerciseService.updateExercise(exercise);
        return ResponseEntity.ok(updatedExercise);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an exercise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exercise successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Exercise not found")
    })
    public ResponseEntity<Void> deleteExercise(@Parameter(description = "ID of the exercise to be deleted", required = true) @PathVariable String id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}