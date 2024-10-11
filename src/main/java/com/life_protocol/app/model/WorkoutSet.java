package com.life_protocol.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "workout_sets")
public class WorkoutSet {
    @Id
    private String id;

    private String workoutId;
    private String exerciseId;
    private int setNumber;
    private int reps;
    private double weight; // in kg
    private int durationSeconds; // for timed exercises
    private LocalDateTime completedAt;
    private String notes;

    // Constructors
    public WorkoutSet() {}

    public WorkoutSet(String workoutId, String exerciseId, int setNumber, int reps, double weight, int durationSeconds) {
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
        this.setNumber = setNumber;
        this.reps = reps;
        this.weight = weight;
        this.durationSeconds = durationSeconds;
        this.completedAt = LocalDateTime.now();
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

    @Override
    public String toString() {
        return "WorkoutSet{" +
                "id='" + id + '\'' +
                ", workoutId='" + workoutId + '\'' +
                ", exerciseId='" + exerciseId + '\'' +
                ", setNumber=" + setNumber +
                ", reps=" + reps +
                ", weight=" + weight +
                ", durationSeconds=" + durationSeconds +
                ", completedAt=" + completedAt +
                ", notes='" + notes + '\'' +
                '}';
    }
}