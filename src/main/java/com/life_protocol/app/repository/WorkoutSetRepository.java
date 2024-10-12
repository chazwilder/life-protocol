
package com.life_protocol.app.repository;

import com.life_protocol.app.model.WorkoutSet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutSetRepository extends MongoRepository<WorkoutSet, String> {
    List<WorkoutSet> findByWorkoutId(String workoutId);
    List<WorkoutSet> findByExerciseId(String exerciseId);
    List<WorkoutSet> findTop10ByExerciseIdOrderByWeightDesc(String exerciseId);
}