package com.life_protocol.app.repository;

import com.life_protocol.app.model.ProtocolReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProtocolReviewRepository extends MongoRepository<ProtocolReview, String> {
    List<ProtocolReview> findByProtocolId(String protocolId);
    List<ProtocolReview> findByUserId(String userId);
    List<ProtocolReview> findByProtocolIdAndUserId(String protocolId, String userId);
    List<ProtocolReview> findByReviewDateBetween(LocalDateTime start, LocalDateTime end);
    List<ProtocolReview> findByRatingGreaterThanEqual(int rating);
}