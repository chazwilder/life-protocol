package com.life_protocol.app.dto;

import java.time.LocalDateTime;

public class AIAdviceDTO {
    private String id;
    private String userId;
    private String category;
    private String advice;
    private LocalDateTime generatedAt;
    private boolean isRead;
    private boolean isImplemented;
    private LocalDateTime implementedAt;
    private int userRating;
    private String userFeedback;

    public AIAdviceDTO() {}

    public AIAdviceDTO(String id, String userId, String category, String advice,
                       LocalDateTime generatedAt, boolean isRead, boolean isImplemented,
                       LocalDateTime implementedAt, int userRating, String userFeedback) {
        this.id = id;
        this.userId = userId;
        this.category = category;
        this.advice = advice;
        this.generatedAt = generatedAt;
        this.isRead = isRead;
        this.isImplemented = isImplemented;
        this.implementedAt = implementedAt;
        this.userRating = userRating;
        this.userFeedback = userFeedback;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isImplemented() {
        return isImplemented;
    }

    public void setImplemented(boolean implemented) {
        isImplemented = implemented;
    }

    public LocalDateTime getImplementedAt() {
        return implementedAt;
    }

    public void setImplementedAt(LocalDateTime implementedAt) {
        this.implementedAt = implementedAt;
    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public String getUserFeedback() {
        return userFeedback;
    }

    public void setUserFeedback(String userFeedback) {
        this.userFeedback = userFeedback;
    }
}