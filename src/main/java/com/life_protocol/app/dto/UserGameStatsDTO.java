package com.life_protocol.app.dto;

import java.time.LocalDateTime;

public class UserGameStatsDTO {
    private String id;
    private String userId;
    private int healthPoints;
    private int experiencePoints;
    private int level;
    private int streak;
    private LocalDateTime lastLogin;
    private LocalDateTime updatedAt;

    public UserGameStatsDTO() {}

    public UserGameStatsDTO(String id, String userId, int healthPoints, int experiencePoints,
                            int level, int streak, LocalDateTime lastLogin, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.healthPoints = healthPoints;
        this.experiencePoints = experiencePoints;
        this.level = level;
        this.streak = streak;
        this.lastLogin = lastLogin;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}