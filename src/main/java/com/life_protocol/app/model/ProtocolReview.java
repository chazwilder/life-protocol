package com.life_protocol.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "protocol_reviews")
public class ProtocolReview {
    @Id
    private String id;

    private String protocolId;
    private String userId;
    private LocalDateTime reviewDate;
    private int rating;
    private String overallComments;
    private List<ReviewItem> reviewItems = new ArrayList<>();

    public static class ReviewItem {
        private String aspect;
        private int rating;
        private String comments;

        public String getAspect() {
            return aspect;
        }

        public void setAspect(String aspect) {
            this.aspect = aspect;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }
    }

    public ProtocolReview() {
    }

    public ProtocolReview(String protocolId, String userId, int rating, String overallComments) {
        this.protocolId = protocolId;
        this.userId = userId;
        this.rating = rating;
        this.overallComments = overallComments;
        this.reviewDate = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(String protocolId) {
        this.protocolId = protocolId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getOverallComments() {
        return overallComments;
    }

    public void setOverallComments(String overallComments) {
        this.overallComments = overallComments;
    }

    public List<ReviewItem> getReviewItems() {
        return reviewItems;
    }

    public void setReviewItems(List<ReviewItem> reviewItems) {
        this.reviewItems = reviewItems;
    }
}