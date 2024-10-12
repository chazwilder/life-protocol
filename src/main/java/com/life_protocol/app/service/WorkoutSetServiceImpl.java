package com.life_protocol.app.service;

import com.life_protocol.app.model.WorkoutSet;
import com.life_protocol.app.repository.WorkoutSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutSetServiceImpl implements WorkoutSetService {

    private final WorkoutSetRepository workoutSetRepository;

    @Autowired
    public WorkoutSetServiceImpl(WorkoutSetRepository workoutSetRepository) {
        this.workoutSetRepository = workoutSetRepository;
    }

    @Override
    public WorkoutSet createWorkoutSet(WorkoutSet workoutSet) {
        workoutSet.setCompletedAt(LocalDateTime.now());
        return workoutSetRepository.save(workoutSet);
    }

    @Override
    public Optional<WorkoutSet> getWorkoutSetById(String id) {
        return workoutSetRepository.findById(id);
    }

    @Override
    public List<WorkoutSet> getWorkoutSetsByWorkoutId(String workoutId) {
        return workoutSetRepository.findByWorkoutId(workoutId);
    }

    @Override
    public List<WorkoutSet> getWorkoutSetsByExerciseId(String exerciseId) {
        return workoutSetRepository.findByExerciseId(exerciseId);
    }

    @Override
    public List<WorkoutSet> getTopWorkoutSetsByExerciseId(String exerciseId, int limit) {
        return workoutSetRepository.findTop10ByExerciseIdOrderByWeightDesc(exerciseId)
                .subList(0, Math.min(limit, 10));
    }

    @Override
    public WorkoutSet updateWorkoutSet(WorkoutSet workoutSet) {
        return workoutSetRepository.save(workoutSet);
    }

    @Override
    public void deleteWorkoutSet(String id) {
        workoutSetRepository.deleteById(id);
    }
}