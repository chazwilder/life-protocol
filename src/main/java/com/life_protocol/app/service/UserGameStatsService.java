package com.life_protocol.app.service;

import com.life_protocol.app.model.UserGameStats;
import java.util.List;
import java.util.Optional;

public interface UserGameStatsService {
    UserGameStats createUserGameStats(UserGameStats userGameStats);
    Optional<UserGameStats> getUserGameStatsById(String id);
    Optional<UserGameStats> getUserGameStatsByUserId(String userId);
    List<UserGameStats> getTopUsersByLevel(int limit);
    List<UserGameStats> getTopUsersByExperiencePoints(int limit);
    UserGameStats updateUserGameStats(UserGameStats userGameStats);
    void deleteUserGameStats(String id);
    void addExperiencePoints(String userId, int points);
    void updateHealthPoints(String userId, int healthPoints);
    void incrementStreak(String userId);
    void resetStreak(String userId);
}