package com.life_protocol.app.dto;

import java.time.LocalDateTime;

public class ProtocolDTO {
    private String id;
    private String title;
    private String description;
    private String userId;
    private String goalId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isCompleted;
    private int frequency;
    private LocalDateTime nextDueDate;

    public ProtocolDTO() {}

    public ProtocolDTO(String id, String title, String description, String userId, String goalId,
                       LocalDateTime createdAt, LocalDateTime updatedAt, boolean isCompleted,
                       int frequency, LocalDateTime nextDueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.goalId = goalId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isCompleted = isCompleted;
        this.frequency = frequency;
        this.nextDueDate = nextDueDate;
    }

    // Getters and setters for all fields
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoalId() {
        return goalId;
    }

    public void setGoalId(String goalId) {
        this.goalId = goalId;
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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public LocalDateTime getNextDueDate() {
        return nextDueDate;
    }

    public void setNextDueDate(LocalDateTime nextDueDate) {
        this.nextDueDate = nextDueDate;
    }
}