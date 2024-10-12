package com.life_protocol.app.repository;

import com.life_protocol.app.model.AIAdvice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AIAdviceRepository extends MongoRepository<AIAdvice, String> {
    List<AIAdvice> findByUserId(String userId);
    List<AIAdvice> findByUserIdAndCategory(String userId, String category);
    List<AIAdvice> findByUserIdAndIsReadFalse(String userId);
    List<AIAdvice> findTop10ByUserIdOrderByGeneratedAtDesc(String userId);
}