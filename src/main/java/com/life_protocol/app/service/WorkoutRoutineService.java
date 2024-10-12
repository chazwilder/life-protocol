package com.life_protocol.app.service;

import com.life_protocol.app.model.WorkoutRoutine;
import java.util.List;
import java.util.Optional;

public interface WorkoutRoutineService {
    WorkoutRoutine createWorkoutRoutine(WorkoutRoutine workoutRoutine);
    Optional<WorkoutRoutine> getWorkoutRoutineById(String id);
    List<WorkoutRoutine> getWorkoutRoutinesByUserId(String userId);
    List<WorkoutRoutine> getWorkoutRoutinesByDifficultyLevel(String userId, String difficultyLevel);
    List<WorkoutRoutine> searchWorkoutRoutines(String name);
    WorkoutRoutine updateWorkoutRoutine(WorkoutRoutine workoutRoutine);
    void deleteWorkoutRoutine(String id);
    void addExerciseToRoutine(String routineId, WorkoutRoutine.WorkoutExercise exercise);
    void removeExerciseFromRoutine(String routineId, String exerciseId);
}