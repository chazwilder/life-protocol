package com.life_protocol.app.service;

import com.life_protocol.app.model.Protocol;
import com.life_protocol.app.repository.ProtocolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProtocolServiceImpl implements ProtocolService {

    private final ProtocolRepository protocolRepository;

    @Autowired
    public ProtocolServiceImpl(ProtocolRepository protocolRepository) {
        this.protocolRepository = protocolRepository;
    }

    @Override
    public Protocol createProtocol(Protocol protocol) {
        // Add any validation or business logic here
        return protocolRepository.save(protocol);
    }

    @Override
    public Optional<Protocol> getProtocolById(String id) {
        return protocolRepository.findById(id);
    }

    @Override
    public List<Protocol> getProtocolsByUserId(String userId) {
        return protocolRepository.findByUserId(userId);
    }

    @Override
    public List<Protocol> getActiveProtocolsByUserId(String userId) {
        return protocolRepository.findByUserIdAndCompleted(userId, false);
    }

    @Override
    public List<Protocol> getDueProtocolsByUserId(String userId, LocalDateTime dueDate) {
        return protocolRepository.findByUserIdAndNextDueDateBefore(userId, dueDate);
    }

    @Override
    public Protocol updateProtocol(Protocol protocol) {
        return protocolRepository.save(protocol);
    }

    @Override
    public void deleteProtocol(String id) {
        protocolRepository.deleteById(id);
    }

    @Override
    public List<Protocol> getProtocolsByGoalId(String goalId) {
        return protocolRepository.findByGoalId(goalId);
    }
}