package com.life_protocol.app.service;

import com.life_protocol.app.model.Workout;
import com.life_protocol.app.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutServiceImpl(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Override
    public Workout createWorkout(Workout workout) {
        workout.setCreatedAt(LocalDateTime.now());
        workout.setUpdatedAt(LocalDateTime.now());
        return workoutRepository.save(workout);
    }

    @Override
    public Optional<Workout> getWorkoutById(String id) {
        return workoutRepository.findById(id);
    }

    @Override
    public List<Workout> getWorkoutsByUserId(String userId) {
        return workoutRepository.findByUserId(userId);
    }

    @Override
    public List<Workout> getWorkoutsByUserIdAndDateRange(String userId, LocalDateTime start, LocalDateTime end) {
        return workoutRepository.findByUserIdAndStartTimeBetween(userId, start, end);
    }

    @Override
    public List<Workout> getRecentWorkoutsByUserId(String userId, int limit) {
        return workoutRepository.findTop10ByUserIdOrderByStartTimeDesc(userId).subList(0, Math.min(limit, 10));
    }

    @Override
    public Workout updateWorkout(Workout workout) {
        workout.setUpdatedAt(LocalDateTime.now());
        return workoutRepository.save(workout);
    }

    @Override
    public void deleteWorkout(String id) {
        workoutRepository.deleteById(id);
    }

    @Override
    public void addExerciseToWorkout(String workoutId, String exerciseId) {
        workoutRepository.findById(workoutId).ifPresent(workout -> {
            workout.getExerciseIds().add(exerciseId);
            workout.setUpdatedAt(LocalDateTime.now());
            workoutRepository.save(workout);
        });
    }

    @Override
    public void removeExerciseFromWorkout(String workoutId, String exerciseId) {
        workoutRepository.findById(workoutId).ifPresent(workout -> {
            workout.getExerciseIds().remove(exerciseId);
            workout.setUpdatedAt(LocalDateTime.now());
            workoutRepository.save(workout);
        });
    }
}