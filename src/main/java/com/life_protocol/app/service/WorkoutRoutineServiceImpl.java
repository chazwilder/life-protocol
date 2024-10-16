package com.life_protocol.app.service;

import com.life_protocol.app.model.WorkoutRoutine;
import com.life_protocol.app.repository.WorkoutRoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutRoutineServiceImpl implements WorkoutRoutineService {

    private final WorkoutRoutineRepository workoutRoutineRepository;

    @Autowired
    public WorkoutRoutineServiceImpl(WorkoutRoutineRepository workoutRoutineRepository) {
        this.workoutRoutineRepository = workoutRoutineRepository;
    }

    @Override
    public WorkoutRoutine createWorkoutRoutine(WorkoutRoutine workoutRoutine) {
        workoutRoutine.setCreatedAt(LocalDateTime.now());
        workoutRoutine.setUpdatedAt(LocalDateTime.now());
        return workoutRoutineRepository.save(workoutRoutine);
    }

    @Override
    public Optional<WorkoutRoutine> getWorkoutRoutineById(String id) {
        return workoutRoutineRepository.findById(id);
    }

    @Override
    public List<WorkoutRoutine> getWorkoutRoutinesByUserId(String userId) {
        return workoutRoutineRepository.findByUserId(userId);
    }

    @Override
    public List<WorkoutRoutine> getWorkoutRoutinesByDifficultyLevel(String userId, String difficultyLevel) {
        return workoutRoutineRepository.findByUserIdAndDifficultyLevel(userId, difficultyLevel);
    }

    @Override
    public List<WorkoutRoutine> searchWorkoutRoutines(String name) {
        return workoutRoutineRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public WorkoutRoutine updateWorkoutRoutine(WorkoutRoutine workoutRoutine) {
        workoutRoutine.setUpdatedAt(LocalDateTime.now());
        return workoutRoutineRepository.save(workoutRoutine);
    }

    @Override
    public void deleteWorkoutRoutine(String id) {
        workoutRoutineRepository.deleteById(id);
    }

    @Override
    public void addExerciseToRoutine(String routineId, WorkoutRoutine.WorkoutExercise exercise) {
        workoutRoutineRepository.findById(routineId).ifPresent(routine -> {
            routine.getExercises().add(exercise);
            routine.setUpdatedAt(LocalDateTime.now());
            workoutRoutineRepository.save(routine);
        });
    }

    @Override
    public void removeExerciseFromRoutine(String routineId, String exerciseId) {
        workoutRoutineRepository.findById(routineId).ifPresent(routine -> {
            routine.getExercises().removeIf(e -> e.getExerciseId().equals(exerciseId));
            routine.setUpdatedAt(LocalDateTime.now());
            workoutRoutineRepository.save(routine);
        });
    }
}