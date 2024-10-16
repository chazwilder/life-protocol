package com.life_protocol.app.repository;

import com.life_protocol.app.model.Protocol;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProtocolRepository extends MongoRepository<Protocol, String> {
    List<Protocol> findByUserId(String userId);
    List<Protocol> findByUserIdAndCompleted(String userId, boolean completed);
    List<Protocol> findByUserIdAndNextDueDateBefore(String userId, LocalDateTime date);
    List<Protocol> findByGoalId(String goalId);
}