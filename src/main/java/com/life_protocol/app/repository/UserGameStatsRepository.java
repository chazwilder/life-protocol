package com.life_protocol.app.repository;

import com.life_protocol.app.model.UserGameStats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserGameStatsRepository extends MongoRepository<UserGameStats, String> {
    Optional<UserGameStats> findByUserId(String userId);
    List<UserGameStats> findTop10ByOrderByLevelDesc();
    List<UserGameStats> findTop10ByOrderByExperiencePointsDesc();
}