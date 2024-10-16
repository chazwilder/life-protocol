
package com.life_protocol.app.repository;

import com.life_protocol.app.model.WorkoutRoutine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRoutineRepository extends MongoRepository<WorkoutRoutine, String> {
    List<WorkoutRoutine> findByUserId(String userId);
    List<WorkoutRoutine> findByUserIdAndDifficultyLevel(String userId, String difficultyLevel);
    List<WorkoutRoutine> findByNameContainingIgnoreCase(String name);
}