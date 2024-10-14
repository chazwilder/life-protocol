package com.life_protocol.app.controller;

import com.life_protocol.app.model.WorkoutSet;
import com.life_protocol.app.service.WorkoutSetService;
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
@RequestMapping("/api/workout-sets")
@Tag(name = "Workout Set Management", description = "Operations pertaining to workout sets in the Life Protocol application")
public class WorkoutSetController {

    private final WorkoutSetService workoutSetService;

    @Autowired
    public WorkoutSetController(WorkoutSetService workoutSetService) {
        this.workoutSetService = workoutSetService;
    }

    @PostMapping
    @Operation(summary = "Create a new workout set")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Workout set successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<WorkoutSet> createWorkoutSet(@Parameter(description = "Workout set object to be created", required = true) @RequestBody WorkoutSet workoutSet) {
        WorkoutSet createdSet = workoutSetService.createWorkoutSet(workoutSet);
        return new ResponseEntity<>(createdSet, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a workout set by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved workout set"),
            @ApiResponse(responseCode = "404", description = "Workout set not found")
    })
    public ResponseEntity<WorkoutSet> getWorkoutSetById(@Parameter(description = "ID of the workout set to be retrieved", required = true) @PathVariable String id) {
        return workoutSetService.getWorkoutSetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/workout/{workoutId}")
    @Operation(summary = "Get workout sets by workout ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of workout sets")
    public ResponseEntity<List<WorkoutSet>> getWorkoutSetsByWorkoutId(@Parameter(description = "ID of the workout", required = true) @PathVariable String workoutId) {
        List<WorkoutSet> sets = workoutSetService.getWorkoutSetsByWorkoutId(workoutId);
        return ResponseEntity.ok(sets);
    }

    @GetMapping("/exercise/{exerciseId}")
    @Operation(summary = "Get workout sets by exercise ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of workout sets")
    public ResponseEntity<List<WorkoutSet>> getWorkoutSetsByExerciseId(@Parameter(description = "ID of the exercise", required = true) @PathVariable String exerciseId) {
        List<WorkoutSet> sets = workoutSetService.getWorkoutSetsByExerciseId(exerciseId);
        return ResponseEntity.ok(sets);
    }

    @GetMapping("/exercise/{exerciseId}/top")
    @Operation(summary = "Get top workout sets by exercise ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of top workout sets")
    public ResponseEntity<List<WorkoutSet>> getTopWorkoutSetsByExerciseId(
            @Parameter(description = "ID of the exercise", required = true) @PathVariable String exerciseId,
            @Parameter(description = "Limit of workout sets to retrieve", required = false) @RequestParam(defaultValue = "10") int limit) {
        List<WorkoutSet> topSets = workoutSetService.getTopWorkoutSetsByExerciseId(exerciseId, limit);
        return ResponseEntity.ok(topSets);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a workout set")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workout set successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Workout set not found")
    })
    public ResponseEntity<WorkoutSet> updateWorkoutSet(
            @Parameter(description = "ID of the workout set to be updated", required = true) @PathVariable String id,
            @Parameter(description = "Updated workout set object", required = true) @RequestBody WorkoutSet workoutSet) {
        if (!id.equals(workoutSet.getId())) {
            return ResponseEntity.badRequest().build();
        }
        WorkoutSet updatedSet = workoutSetService.updateWorkoutSet(workoutSet);
        return ResponseEntity.ok(updatedSet);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a workout set")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Workout set successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Workout set not found")
    })
    public ResponseEntity<Void> deleteWorkoutSet(@Parameter(description = "ID of the workout set to be deleted", required = true) @PathVariable String id) {
        workoutSetService.deleteWorkoutSet(id);
        return ResponseEntity.noContent().build();
    }
}