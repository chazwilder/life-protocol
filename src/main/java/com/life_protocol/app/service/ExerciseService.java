package com.life_protocol.app.service;

import com.life_protocol.app.model.Exercise;
import java.util.List;
import java.util.Optional;

public interface ExerciseService {
    Exercise createExercise(Exercise exercise);
    Optional<Exercise> getExerciseById(String id);
    List<Exercise> getAllExercises();
    List<Exercise> getExercisesByCategory(String category);
    List<Exercise> getExercisesByMuscleGroup(String muscleGroup);
    List<Exercise> searchExercises(String name);
    Exercise updateExercise(Exercise exercise);
    void deleteExercise(String id);
}