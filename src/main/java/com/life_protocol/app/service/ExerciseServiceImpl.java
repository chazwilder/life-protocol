package com.life_protocol.app.service;

import com.life_protocol.app.model.Exercise;
import com.life_protocol.app.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Exercise createExercise(Exercise exercise) {
        exercise.setCreatedAt(LocalDateTime.now());
        exercise.setUpdatedAt(LocalDateTime.now());
        return exerciseRepository.save(exercise);
    }

    @Override
    public Optional<Exercise> getExerciseById(String id) {
        return exerciseRepository.findById(id);
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public List<Exercise> getExercisesByCategory(String category) {
        return exerciseRepository.findByCategory(category);
    }

    @Override
    public List<Exercise> getExercisesByMuscleGroup(String muscleGroup) {
        return exerciseRepository.findByMuscleGroup(muscleGroup);
    }

    @Override
    public List<Exercise> searchExercises(String name) {
        return exerciseRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Exercise updateExercise(Exercise exercise) {
        exercise.setUpdatedAt(LocalDateTime.now());
        return exerciseRepository.save(exercise);
    }

    @Override
    public void deleteExercise(String id) {
        exerciseRepository.deleteById(id);
    }
}