package com.life_protocol.app.controller;

import com.life_protocol.app.model.WorkoutSet;
import com.life_protocol.app.service.WorkoutSetService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-sets")
@Api(tags = "Workout Set Management", description = "Operations pertaining to workout sets in the Life Protocol application")
public class WorkoutSetController {

    private final WorkoutSetService workoutSetService;

    @Autowired
    public WorkoutSetController(WorkoutSetService workoutSetService) {
        this.workoutSetService = workoutSetService;
    }

    @PostMapping
    @ApiOperation(value = "Create a new workout set", response = WorkoutSet.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Workout set successfully created"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<WorkoutSet> createWorkoutSet(@ApiParam(value = "Workout set object to be created", required = true) @RequestBody WorkoutSet workoutSet) {
        WorkoutSet createdSet = workoutSetService.createWorkoutSet(workoutSet);
        return new ResponseEntity<>(createdSet, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a workout set by ID", response = WorkoutSet.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved workout set"),
            @ApiResponse(code = 404, message = "Workout set not found")
    })
    public ResponseEntity<WorkoutSet> getWorkoutSetById(@ApiParam(value = "ID of the workout set to be retrieved", required = true) @PathVariable String id) {
        return workoutSetService.getWorkoutSetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/workout/{workoutId}")
    @ApiOperation(value = "Get workout sets by workout ID", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of workout sets")
    public ResponseEntity<List<WorkoutSet>> getWorkoutSetsByWorkoutId(@ApiParam(value = "ID of the workout", required = true) @PathVariable String workoutId) {
        List<WorkoutSet> sets = workoutSetService.getWorkoutSetsByWorkoutId(workoutId);
        return ResponseEntity.ok(sets);
    }

    @GetMapping("/exercise/{exerciseId}")
    @ApiOperation(value = "Get workout sets by exercise ID", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of workout sets")
    public ResponseEntity<List<WorkoutSet>> getWorkoutSetsByExerciseId(@ApiParam(value = "ID of the exercise", required = true) @PathVariable String exerciseId) {
        List<WorkoutSet> sets = workoutSetService.getWorkoutSetsByExerciseId(exerciseId);
        return ResponseEntity.ok(sets);
    }

    @GetMapping("/exercise/{exerciseId}/top")
    @ApiOperation(value = "Get top workout sets by exercise ID", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of top workout sets")
    public ResponseEntity<List<WorkoutSet>> getTopWorkoutSetsByExerciseId(
            @ApiParam(value = "ID of the exercise", required = true) @PathVariable String exerciseId,
            @ApiParam(value = "Limit of workout sets to retrieve", defaultValue = "10") @RequestParam(defaultValue = "10") int limit) {
        List<WorkoutSet> topSets = workoutSetService.getTopWorkoutSetsByExerciseId(exerciseId, limit);
        return ResponseEntity.ok(topSets);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a workout set", response = WorkoutSet.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Workout set successfully updated"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "Workout set not found")
    })
    public ResponseEntity<WorkoutSet> updateWorkoutSet(
            @ApiParam(value = "ID of the workout set to be updated", required = true) @PathVariable String id,
            @ApiParam(value = "Updated workout set object", required = true) @RequestBody WorkoutSet workoutSet) {
        if (!id.equals(workoutSet.getId())) {
            return ResponseEntity.badRequest().build();
        }
        WorkoutSet updatedSet = workoutSetService.updateWorkoutSet(workoutSet);
        return ResponseEntity.ok(updatedSet);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a workout set")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Workout set successfully deleted"),
            @ApiResponse(code = 404, message = "Workout set not found")
    })
    public ResponseEntity<Void> deleteWorkoutSet(@ApiParam(value = "ID of the workout set to be deleted", required = true) @PathVariable String id) {
        workoutSetService.deleteWorkoutSet(id);
        return ResponseEntity.noContent().build();
    }
}