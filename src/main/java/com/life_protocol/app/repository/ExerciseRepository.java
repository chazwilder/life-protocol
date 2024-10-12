
package com.life_protocol.app.repository;

import com.life_protocol.app.model.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends MongoRepository<Exercise, String> {
    List<Exercise> findByCategory(String category);
    List<Exercise> findByMuscleGroup(String muscleGroup);
    List<Exercise> findByNameContainingIgnoreCase(String name);
}