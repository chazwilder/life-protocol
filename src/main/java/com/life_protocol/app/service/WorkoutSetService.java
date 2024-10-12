package com.life_protocol.app.service;

import com.life_protocol.app.model.WorkoutSet;
import java.util.List;
import java.util.Optional;

public interface WorkoutSetService {
    WorkoutSet createWorkoutSet(WorkoutSet workoutSet);
    Optional<WorkoutSet> getWorkoutSetById(String id);
    List<WorkoutSet> getWorkoutSetsByWorkoutId(String workoutId);
    List<WorkoutSet> getWorkoutSetsByExerciseId(String exerciseId);
    List<WorkoutSet> getTopWorkoutSetsByExerciseId(String exerciseId, int limit);
    WorkoutSet updateWorkoutSet(WorkoutSet workoutSet);
    void deleteWorkoutSet(String id);
}