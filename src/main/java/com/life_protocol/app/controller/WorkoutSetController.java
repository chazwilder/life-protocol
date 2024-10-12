package com.life_protocol.app.controller;

import com.life_protocol.app.model.WorkoutSet;
import com.life_protocol.app.service.WorkoutSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-sets")
public class WorkoutSetController {

    private final WorkoutSetService workoutSetService;

    @Autowired
    public WorkoutSetController(WorkoutSetService workoutSetService) {
        this.workoutSetService = workoutSetService;
    }

    @PostMapping
    public ResponseEntity<WorkoutSet> createWorkoutSet(@RequestBody WorkoutSet workoutSet) {
        WorkoutSet createdSet = workoutSetService.createWorkoutSet(workoutSet);
        return new ResponseEntity<>(createdSet, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutSet> getWorkoutSetById(@PathVariable String id) {
        return workoutSetService.getWorkoutSetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/workout/{workoutId}")
    public ResponseEntity<List<WorkoutSet>> getWorkoutSetsByWorkoutId(@PathVariable String workoutId) {
        List<WorkoutSet> sets = workoutSetService.getWorkoutSetsByWorkoutId(workoutId);
        return ResponseEntity.ok(sets);
    }

    @GetMapping("/exercise/{exerciseId}")
    public ResponseEntity<List<WorkoutSet>> getWorkoutSetsByExerciseId(@PathVariable String exerciseId) {
        List<WorkoutSet> sets = workoutSetService.getWorkoutSetsByExerciseId(exerciseId);
        return ResponseEntity.ok(sets);
    }

    @GetMapping("/exercise/{exerciseId}/top")
    public ResponseEntity<List<WorkoutSet>> getTopWorkoutSetsByExerciseId(
            @PathVariable String exerciseId,
            @RequestParam(defaultValue = "10") int limit) {
        List<WorkoutSet> topSets = workoutSetService.getTopWorkoutSetsByExerciseId(exerciseId, limit);
        return ResponseEntity.ok(topSets);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutSet> updateWorkoutSet(@PathVariable String id, @RequestBody WorkoutSet workoutSet) {
        if (!id.equals(workoutSet.getId())) {
            return ResponseEntity.badRequest().build();
        }
        WorkoutSet updatedSet = workoutSetService.updateWorkoutSet(workoutSet);
        return ResponseEntity.ok(updatedSet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutSet(@PathVariable String id) {
        workoutSetService.deleteWorkoutSet(id);
        return ResponseEntity.noContent().build();
    }
}