package com.life_protocol.app.service;

import com.life_protocol.app.model.ProtocolReview;
import com.life_protocol.app.repository.ProtocolReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProtocolReviewServiceImpl implements ProtocolReviewService {

    private final ProtocolReviewRepository protocolReviewRepository;

    @Autowired
    public ProtocolReviewServiceImpl(ProtocolReviewRepository protocolReviewRepository) {
        this.protocolReviewRepository = protocolReviewRepository;
    }

    @Override
    public ProtocolReview createReview(ProtocolReview review) {
        review.setReviewDate(LocalDateTime.now());
        return protocolReviewRepository.save(review);
    }

    @Override
    public Optional<ProtocolReview> getReviewById(String id) {
        return protocolReviewRepository.findById(id);
    }

    @Override
    public List<ProtocolReview> getReviewsByProtocolId(String protocolId) {
        return protocolReviewRepository.findByProtocolId(protocolId);
    }

    @Override
    public List<ProtocolReview> getReviewsByUserId(String userId) {
        return protocolReviewRepository.findByUserId(userId);
    }

    @Override
    public List<ProtocolReview> getReviewsByDateRange(LocalDateTime start, LocalDateTime end) {
        return protocolReviewRepository.findByReviewDateBetween(start, end);
    }

    @Override
    public List<ProtocolReview> getReviewsByRatingGreaterThan(int rating) {
        return protocolReviewRepository.findByRatingGreaterThanEqual(rating);
    }

    @Override
    public ProtocolReview updateReview(ProtocolReview review) {
        return protocolReviewRepository.save(review);
    }

    @Override
    public void deleteReview(String id) {
        protocolReviewRepository.deleteById(id);
    }
}