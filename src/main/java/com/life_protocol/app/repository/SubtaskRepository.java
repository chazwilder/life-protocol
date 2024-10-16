package com.life_protocol.app.repository;

import com.life_protocol.app.model.Subtask;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubtaskRepository extends MongoRepository<Subtask, String> {
    List<Subtask> findByProtocolId(String protocolId);
    List<Subtask> findByProtocolIdAndCompleted(String protocolId, boolean completed);
}