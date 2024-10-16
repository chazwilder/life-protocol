package com.life_protocol.app.service;

import com.life_protocol.app.model.AIAdvice;
import com.life_protocol.app.repository.AIAdviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AIAdviceServiceImpl implements AIAdviceService {

    private final AIAdviceRepository aiAdviceRepository;

    @Autowired
    public AIAdviceServiceImpl(AIAdviceRepository aiAdviceRepository) {
        this.aiAdviceRepository = aiAdviceRepository;
    }

    @Override
    public AIAdvice createAIAdvice(AIAdvice aiAdvice) {
        aiAdvice.setGeneratedAt(LocalDateTime.now());
        return aiAdviceRepository.save(aiAdvice);
    }

    @Override
    public Optional<AIAdvice> getAIAdviceById(String id) {
        return aiAdviceRepository.findById(id);
    }

    @Override
    public List<AIAdvice> getAIAdviceByUserId(String userId) {
        return aiAdviceRepository.findByUserId(userId);
    }

    @Override
    public List<AIAdvice> getAIAdviceByUserIdAndCategory(String userId, String category) {
        return aiAdviceRepository.findByUserIdAndCategory(userId, category);
    }

    @Override
    public List<AIAdvice> getUnreadAIAdviceByUserId(String userId) {
        return aiAdviceRepository.findByUserIdAndIsReadFalse(userId);
    }

    @Override
    public List<AIAdvice> getRecentAIAdviceByUserId(String userId, int limit) {
        return aiAdviceRepository.findTop10ByUserIdOrderByGeneratedAtDesc(userId).subList(0, Math.min(limit, 10));
    }

    @Override
    public AIAdvice updateAIAdvice(AIAdvice aiAdvice) {
        return aiAdviceRepository.save(aiAdvice);
    }

    @Override
    public void deleteAIAdvice(String id) {
        aiAdviceRepository.deleteById(id);
    }

    @Override
    public void markAIAdviceAsRead(String id) {
        aiAdviceRepository.findById(id).ifPresent(advice -> {
            advice.setRead(true);
            aiAdviceRepository.save(advice);
        });
    }

    @Override
    public void markAIAdviceAsImplemented(String id) {
        aiAdviceRepository.findById(id).ifPresent(advice -> {
            advice.setImplemented(true);
            advice.setImplementedAt(LocalDateTime.now());
            aiAdviceRepository.save(advice);
        });
    }

    @Override
    public void rateAIAdvice(String id, int rating) {
        aiAdviceRepository.findById(id).ifPresent(advice -> {
            advice.setUserRating(rating);
            aiAdviceRepository.save(advice);
        });
    }

    @Override
    public void provideFeedbackForAIAdvice(String id, String feedback) {
        aiAdviceRepository.findById(id).ifPresent(advice -> {
            advice.setUserFeedback(feedback);
            aiAdviceRepository.save(advice);
        });
    }
}