package com.life_protocol.app.controller;

import com.life_protocol.app.model.Exercise;
import com.life_protocol.app.service.ExerciseService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@Api(tags = "Exercise Management", description = "Operations pertaining to exercises in the Life Protocol application")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping
    @ApiOperation(value = "Create a new exercise", response = Exercise.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Exercise successfully created"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<Exercise> createExercise(@ApiParam(value = "Exercise object to be created", required = true) @RequestBody Exercise exercise) {
        Exercise createdExercise = exerciseService.createExercise(exercise);
        return new ResponseEntity<>(createdExercise, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get an exercise by ID", response = Exercise.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved exercise"),
            @ApiResponse(code = 404, message = "Exercise not found")
    })
    public ResponseEntity<Exercise> getExerciseById(@ApiParam(value = "ID of the exercise to be retrieved", required = true) @PathVariable String id) {
        return exerciseService.getExerciseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @ApiOperation(value = "Get all exercises", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of exercises")
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseService.getAllExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/category/{category}")
    @ApiOperation(value = "Get exercises by category", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of exercises")
    public ResponseEntity<List<Exercise>> getExercisesByCategory(@ApiParam(value = "Category of exercises to retrieve", required = true) @PathVariable String category) {
        List<Exercise> exercises = exerciseService.getExercisesByCategory(category);
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/muscle-group/{muscleGroup}")
    @ApiOperation(value = "Get exercises by muscle group", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of exercises")
    public ResponseEntity<List<Exercise>> getExercisesByMuscleGroup(@ApiParam(value = "Muscle group of exercises to retrieve", required = true) @PathVariable String muscleGroup) {
        List<Exercise> exercises = exerciseService.getExercisesByMuscleGroup(muscleGroup);
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/search")
    @ApiOperation(value = "Search exercises by name", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of exercises")
    public ResponseEntity<List<Exercise>> searchExercises(@ApiParam(value = "Name to search for", required = true) @RequestParam String name) {
        List<Exercise> exercises = exerciseService.searchExercises(name);
        return ResponseEntity.ok(exercises);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an exercise", response = Exercise.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exercise successfully updated"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "Exercise not found")
    })
    public ResponseEntity<Exercise> updateExercise(
            @ApiParam(value = "ID of the exercise to be updated", required = true) @PathVariable String id,
            @ApiParam(value = "Updated exercise object", required = true) @RequestBody Exercise exercise) {
        if (!id.equals(exercise.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Exercise updatedExercise = exerciseService.updateExercise(exercise);
        return ResponseEntity.ok(updatedExercise);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an exercise")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Exercise successfully deleted"),
            @ApiResponse(code = 404, message = "Exercise not found")
    })
    public ResponseEntity<Void> deleteExercise(@ApiParam(value = "ID of the exercise to be deleted", required = true) @PathVariable String id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}