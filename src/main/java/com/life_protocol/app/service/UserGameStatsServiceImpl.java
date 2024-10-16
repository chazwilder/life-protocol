package com.life_protocol.app.service;

import com.life_protocol.app.model.UserGameStats;
import com.life_protocol.app.repository.UserGameStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserGameStatsServiceImpl implements UserGameStatsService {

    private final UserGameStatsRepository userGameStatsRepository;

    @Autowired
    public UserGameStatsServiceImpl(UserGameStatsRepository userGameStatsRepository) {
        this.userGameStatsRepository = userGameStatsRepository;
    }

    @Override
    public UserGameStats createUserGameStats(UserGameStats userGameStats) {
        userGameStats.setUpdatedAt(LocalDateTime.now());
        return userGameStatsRepository.save(userGameStats);
    }

    @Override
    public Optional<UserGameStats> getUserGameStatsById(String id) {
        return userGameStatsRepository.findById(id);
    }

    @Override
    public Optional<UserGameStats> getUserGameStatsByUserId(String userId) {
        return userGameStatsRepository.findByUserId(userId);
    }

    @Override
    public List<UserGameStats> getTopUsersByLevel(int limit) {
        return userGameStatsRepository.findTop10ByOrderByLevelDesc().subList(0, Math.min(limit, 10));
    }

    @Override
    public List<UserGameStats> getTopUsersByExperiencePoints(int limit) {
        return userGameStatsRepository.findTop10ByOrderByExperiencePointsDesc().subList(0, Math.min(limit, 10));
    }

    @Override
    public UserGameStats updateUserGameStats(UserGameStats userGameStats) {
        userGameStats.setUpdatedAt(LocalDateTime.now());
        return userGameStatsRepository.save(userGameStats);
    }

    @Override
    public void deleteUserGameStats(String id) {
        userGameStatsRepository.deleteById(id);
    }

    @Override
    public void addExperiencePoints(String userId, int points) {
        userGameStatsRepository.findByUserId(userId).ifPresent(stats -> {
            stats.setExperiencePoints(stats.getExperiencePoints() + points);
            stats.setUpdatedAt(LocalDateTime.now());
            userGameStatsRepository.save(stats);
        });
    }

    @Override
    public void updateHealthPoints(String userId, int healthPoints) {
        userGameStatsRepository.findByUserId(userId).ifPresent(stats -> {
            stats.setHealthPoints(healthPoints);
            stats.setUpdatedAt(LocalDateTime.now());
            userGameStatsRepository.save(stats);
        });
    }

    @Override
    public void incrementStreak(String userId) {
        userGameStatsRepository.findByUserId(userId).ifPresent(stats -> {
            stats.setStreak(stats.getStreak() + 1);
            stats.setUpdatedAt(LocalDateTime.now());
            userGameStatsRepository.save(stats);
        });
    }

    @Override
    public void resetStreak(String userId) {
        userGameStatsRepository.findByUserId(userId).ifPresent(stats -> {
            stats.setStreak(0);
            stats.setUpdatedAt(LocalDateTime.now());
            userGameStatsRepository.save(stats);
        });
    }
}