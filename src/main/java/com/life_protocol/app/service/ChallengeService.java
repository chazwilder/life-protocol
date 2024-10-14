package com.life_protocol.app.service;

import com.life_protocol.app.model.Challenge;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ChallengeService {
    Challenge createChallenge(Challenge challenge);
    Optional<Challenge> getChallengeById(String id);
    List<Challenge> getChallengesByCreator(String creatorId);
    List<Challenge> getChallengesByParticipant(String userId);
    List<Challenge> getActiveAndUpcomingChallenges();
    List<Challenge> getChallengesByStatus(Challenge.ChallengeStatus status);
    List<Challenge> getChallengesByType(Challenge.ChallengeType type);
    Challenge updateChallenge(Challenge challenge);
    void deleteChallenge(String id);
    void addParticipant(String challengeId, String userId);
    void removeParticipant(String challengeId, String userId);
}