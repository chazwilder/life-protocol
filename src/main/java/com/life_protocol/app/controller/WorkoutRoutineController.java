package com.life_protocol.app.controller;

import com.life_protocol.app.model.WorkoutRoutine;
import com.life_protocol.app.service.WorkoutRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-routines")
public class WorkoutRoutineController {

    private final WorkoutRoutineService workoutRoutineService;

    @Autowired
    public WorkoutRoutineController(WorkoutRoutineService workoutRoutineService) {
        this.workoutRoutineService = workoutRoutineService;
    }

    @PostMapping
    public ResponseEntity<WorkoutRoutine> createWorkoutRoutine(@RequestBody WorkoutRoutine workoutRoutine) {
        WorkoutRoutine createdRoutine = workoutRoutineService.createWorkoutRoutine(workoutRoutine);
        return new ResponseEntity<>(createdRoutine, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutRoutine> getWorkoutRoutineById(@PathVariable String id) {
        return workoutRoutineService.getWorkoutRoutineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkoutRoutine>> getWorkoutRoutinesByUserId(@PathVariable String userId) {
        List<WorkoutRoutine> routines = workoutRoutineService.getWorkoutRoutinesByUserId(userId);
        return ResponseEntity.ok(routines);
    }

    @GetMapping("/user/{userId}/difficulty/{difficultyLevel}")
    public ResponseEntity<List<WorkoutRoutine>> getWorkoutRoutinesByDifficultyLevel(
            @PathVariable String userId, @PathVariable String difficultyLevel) {
        List<WorkoutRoutine> routines = workoutRoutineService.getWorkoutRoutinesByDifficultyLevel(userId, difficultyLevel);
        return ResponseEntity.ok(routines);
    }

    @GetMapping("/search")
    public ResponseEntity<List<WorkoutRoutine>> searchWorkoutRoutines(@RequestParam String name) {
        List<WorkoutRoutine> routines = workoutRoutineService.searchWorkoutRoutines(name);
        return ResponseEntity.ok(routines);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutRoutine> updateWorkoutRoutine(@PathVariable String id, @RequestBody WorkoutRoutine workoutRoutine) {
        if (!id.equals(workoutRoutine.getId())) {
            return ResponseEntity.badRequest().build();
        }
        WorkoutRoutine updatedRoutine = workoutRoutineService.updateWorkoutRoutine(workoutRoutine);
        return ResponseEntity.ok(updatedRoutine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutRoutine(@PathVariable String id) {
        workoutRoutineService.deleteWorkoutRoutine(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{routineId}/exercises")
    public ResponseEntity<Void> addExerciseToRoutine(
            @PathVariable String routineId,
            @RequestBody WorkoutRoutine.WorkoutExercise exercise) {
        workoutRoutineService.addExerciseToRoutine(routineId, exercise);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{routineId}/exercises/{exerciseId}")
    public ResponseEntity<Void> removeExerciseFromRoutine(
            @PathVariable String routineId,
            @PathVariable String exerciseId) {
        workoutRoutineService.removeExerciseFromRoutine(routineId, exerciseId);
        return ResponseEntity.noContent().build();
    }
}