package com.life_protocol.app.service;

import com.life_protocol.app.model.ProtocolReview;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProtocolReviewService {
    ProtocolReview createReview(ProtocolReview review);
    Optional<ProtocolReview> getReviewById(String id);
    List<ProtocolReview> getReviewsByProtocolId(String protocolId);
    List<ProtocolReview> getReviewsByUserId(String userId);
    List<ProtocolReview> getReviewsByDateRange(LocalDateTime start, LocalDateTime end);
    List<ProtocolReview> getReviewsByRatingGreaterThan(int rating);
    ProtocolReview updateReview(ProtocolReview review);
    void deleteReview(String id);
}