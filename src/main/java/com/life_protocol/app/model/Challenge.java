package com.life_protocol.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "challenges")
public class Challenge {
    @Id
    private String id;

    private String name;
    private String description;
    private String creatorId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ChallengeType type;
    private ChallengeStatus status;

    private List<String> participantIds = new ArrayList<>();

    public enum ChallengeType {
        FITNESS, NUTRITION, PRODUCTIVITY, CUSTOM
    }

    public enum ChallengeStatus {
        UPCOMING, ACTIVE, COMPLETED
    }

    public Challenge() {
    }

    public Challenge(String name, String description, String creatorId, LocalDateTime startDate, LocalDateTime endDate, ChallengeType type) {
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.status = ChallengeStatus.UPCOMING;
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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public ChallengeType getType() {
        return type;
    }

    public void setType(ChallengeType type) {
        this.type = type;
    }

    public ChallengeStatus getStatus() {
        return status;
    }

    public void setStatus(ChallengeStatus status) {
        this.status = status;
    }

    public List<String> getParticipantIds() {
        return participantIds;
    }

    public void setParticipantIds(List<String> participantIds) {
        this.participantIds = participantIds;
    }
}