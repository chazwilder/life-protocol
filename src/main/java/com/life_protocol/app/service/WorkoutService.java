package com.life_protocol.app.service;

import com.life_protocol.app.model.Workout;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WorkoutService {
    Workout createWorkout(Workout workout);
    Optional<Workout> getWorkoutById(String id);
    List<Workout> getWorkoutsByUserId(String userId);
    List<Workout> getWorkoutsByUserIdAndDateRange(String userId, LocalDateTime start, LocalDateTime end);
    List<Workout> getRecentWorkoutsByUserId(String userId, int limit);
    Workout updateWorkout(Workout workout);
    void deleteWorkout(String id);
    void addExerciseToWorkout(String workoutId, String exerciseId);
    void removeExerciseFromWorkout(String workoutId, String exerciseId);
}