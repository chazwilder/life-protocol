
package com.life_protocol.app.repository;

import com.life_protocol.app.model.Achievement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends MongoRepository<Achievement, String> {
    List<Achievement> findByCategory(String category);
    List<Achievement> findByExperiencePointsLessThanEqual(int experiencePoints);
}