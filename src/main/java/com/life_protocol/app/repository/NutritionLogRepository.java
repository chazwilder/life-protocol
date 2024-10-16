
package com.life_protocol.app.repository;

import com.life_protocol.app.model.NutritionLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NutritionLogRepository extends MongoRepository<NutritionLog, String> {
    List<NutritionLog> findByUserId(String userId);
    List<NutritionLog> findByUserIdAndDateBetween(String userId, LocalDateTime start, LocalDateTime end);
    NutritionLog findByUserIdAndDate(String userId, LocalDateTime date);
}