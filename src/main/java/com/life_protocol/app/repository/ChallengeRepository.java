package com.life_protocol.app.repository;

import com.life_protocol.app.model.Challenge;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChallengeRepository extends MongoRepository<Challenge, String> {
    List<Challenge> findByCreatorId(String creatorId);
    List<Challenge> findByParticipantsContaining(String userId);
    List<Challenge> findByStartDateBeforeAndEndDateAfter(LocalDateTime now, LocalDateTime now1);
    List<Challenge> findByStatus(Challenge.ChallengeStatus status);
    List<Challenge> findByType(Challenge.ChallengeType type);
}