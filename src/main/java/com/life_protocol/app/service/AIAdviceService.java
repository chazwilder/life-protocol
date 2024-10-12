package com.life_protocol.app.service;

import com.life_protocol.app.model.AIAdvice;
import java.util.List;
import java.util.Optional;

public interface AIAdviceService {
    AIAdvice createAIAdvice(AIAdvice aiAdvice);
    Optional<AIAdvice> getAIAdviceById(String id);
    List<AIAdvice> getAIAdviceByUserId(String userId);
    List<AIAdvice> getAIAdviceByUserIdAndCategory(String userId, String category);
    List<AIAdvice> getUnreadAIAdviceByUserId(String userId);
    List<AIAdvice> getRecentAIAdviceByUserId(String userId, int limit);
    AIAdvice updateAIAdvice(AIAdvice aiAdvice);
    void deleteAIAdvice(String id);
    void markAIAdviceAsRead(String id);
    void markAIAdviceAsImplemented(String id);
    void rateAIAdvice(String id, int rating);
    void provideFeedbackForAIAdvice(String id, String feedback);
}