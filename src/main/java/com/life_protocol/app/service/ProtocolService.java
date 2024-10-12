package com.life_protocol.app.service;

import com.life_protocol.app.model.Protocol;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProtocolService {
    Protocol createProtocol(Protocol protocol);
    Optional<Protocol> getProtocolById(String id);
    List<Protocol> getProtocolsByUserId(String userId);
    List<Protocol> getActiveProtocolsByUserId(String userId);
    List<Protocol> getDueProtocolsByUserId(String userId, LocalDateTime dueDate);
    Protocol updateProtocol(Protocol protocol);
    void deleteProtocol(String id);
    List<Protocol> getProtocolsByGoalId(String goalId);
}