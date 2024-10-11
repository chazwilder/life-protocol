package com.life_protocol.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "workout_routines")
public class WorkoutRoutine {
    @Id
    private String id;

    private String userId;
    private String name;
    private String description;
    private List<WorkoutExercise> exercises;
    private int estimatedDurationMinutes;
    private String difficultyLevel; // e.g., "Beginner", "Intermediate", "Advanced"
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Inner class to represent exercises in the routine
    public static class WorkoutExercise {
        private String exerciseId;
        private int sets;
        private int repsPerSet;
        private int durationSeconds; // For timed exercises

        // Constructors, getters, and setters for WorkoutExercise
        // ...
    }

    // Constructors
    public WorkoutRoutine() {}

    public WorkoutRoutine(String userId, String name, String description, List<WorkoutExercise> exercises, int estimatedDurationMinutes, String difficultyLevel) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.exercises = exercises;
        this.estimatedDurationMinutes = estimatedDurationMinutes;
        this.difficultyLevel = difficultyLevel;
        this.createdAt = LocalDateTime.now();
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

    public List<WorkoutExercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<WorkoutExercise> exercises) {
        this.exercises = exercises;
    }

    public int getEstimatedDurationMinutes() {
        return estimatedDurationMinutes;
    }

    public void setEstimatedDurationMinutes(int estimatedDurationMinutes) {
        this.estimatedDurationMinutes = estimatedDurationMinutes;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
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

    @Override
    public String toString() {
        return "WorkoutRoutine{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", exercises=" + exercises +
                ", estimatedDurationMinutes=" + estimatedDurationMinutes +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}