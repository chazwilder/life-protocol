package com.life_protocol.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "user_game_stats")
public class UserGameStats {
    @Id
    private String id;

    private String userId;
    private int healthPoints;
    private int experiencePoints;
    private int level;
    private int streak;
    private LocalDateTime lastLogin;
    private LocalDateTime updatedAt;

    // Constructors
    public UserGameStats() {}

    public UserGameStats(String userId) {
        this.userId = userId;
        this.healthPoints = 100;  // Starting HP
        this.experiencePoints = 0;
        this.level = 1;
        this.streak = 0;
        this.lastLogin = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and setters
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

    @Override
    public String toString() {
        return "UserGameStats{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", healthPoints=" + healthPoints +
                ", experiencePoints=" + experiencePoints +
                ", level=" + level +
                ", streak=" + streak +
                ", lastLogin=" + lastLogin +
                ", updatedAt=" + updatedAt +
                '}';
    }
}