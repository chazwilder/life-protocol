package com.life_protocol.app.dto;

import java.time.LocalDateTime;

public class WorkoutSetDTO {
    private String id;
    private String workoutId;
    private String exerciseId;
    private int setNumber;
    private int reps;
    private double weight;
    private int durationSeconds;
    private LocalDateTime completedAt;
    private String notes;

    public WorkoutSetDTO() {}

    public WorkoutSetDTO(String id, String workoutId, String exerciseId, int setNumber,
                         int reps, double weight, int durationSeconds, LocalDateTime completedAt,
                         String notes) {
        this.id = id;
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
        this.setNumber = setNumber;
        this.reps = reps;
        this.weight = weight;
        this.durationSeconds = durationSeconds;
        this.completedAt = completedAt;
        this.notes = notes;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(String workoutId) {
        this.workoutId = workoutId;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}