package com.life_protocol.app.dto;

import java.time.LocalDateTime;

public class AchievementDTO {
    private String id;
    private String name;
    private String description;
    private int experiencePoints;
    private String badgeImageUrl;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AchievementDTO() {}

    public AchievementDTO(String id, String name, String description, int experiencePoints,
                          String badgeImageUrl, String category, LocalDateTime createdAt,
                          LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.experiencePoints = experiencePoints;
        this.badgeImageUrl = badgeImageUrl;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public String getBadgeImageUrl() {
        return badgeImageUrl;
    }

    public void setBadgeImageUrl(String badgeImageUrl) {
        this.badgeImageUrl = badgeImageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}