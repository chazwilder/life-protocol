
package com.life_protocol.app.repository;

import com.life_protocol.app.model.Workout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkoutRepository extends MongoRepository<Workout, String> {
    List<Workout> findByUserId(String userId);
    List<Workout> findByUserIdAndStartTimeBetween(String userId, LocalDateTime start, LocalDateTime end);
    List<Workout> findTop10ByUserIdOrderByStartTimeDesc(String userId);
}