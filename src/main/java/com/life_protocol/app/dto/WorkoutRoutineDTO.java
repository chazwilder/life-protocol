package com.life_protocol.app.dto;

import java.time.LocalDateTime;
import java.util.List;

public class WorkoutRoutineDTO {
    private String id;
    private String userId;
    private String name;
    private String description;
    private List<WorkoutExerciseDTO> exercises;
    private int estimatedDurationMinutes;
    private String difficultyLevel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static class WorkoutExerciseDTO {
        private String exerciseId;
        private int sets;
        private int repsPerSet;
        private int durationSeconds;

        public WorkoutExerciseDTO() {}

        public WorkoutExerciseDTO(String exerciseId, int sets, int repsPerSet, int durationSeconds) {
            this.exerciseId = exerciseId;
            this.sets = sets;
            this.repsPerSet = repsPerSet;
            this.durationSeconds = durationSeconds;
        }

        // Getters and setters for WorkoutExerciseDTO
        public String getExerciseId() {
            return exerciseId;
        }

        public void setExerciseId(String exerciseId) {
            this.exerciseId = exerciseId;
        }

        public int getSets() {
            return sets;
        }

        public void setSets(int sets) {
            this.sets = sets;
        }

        public int getRepsPerSet() {
            return repsPerSet;
        }

        public void setRepsPerSet(int repsPerSet) {
            this.repsPerSet = repsPerSet;
        }

        public int getDurationSeconds() {
            return durationSeconds;
        }

        public void setDurationSeconds(int durationSeconds) {
            this.durationSeconds = durationSeconds;
        }
    }

    public WorkoutRoutineDTO() {}

    public WorkoutRoutineDTO(String id, String userId, String name, String description,
                             List<WorkoutExerciseDTO> exercises, int estimatedDurationMinutes,
                             String difficultyLevel, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.exercises = exercises;
        this.estimatedDurationMinutes = estimatedDurationMinutes;
        this.difficultyLevel = difficultyLevel;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and setters for WorkoutRoutineDTO
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

    public List<WorkoutExerciseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<WorkoutExerciseDTO> exercises) {
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
}