package com.life_protocol.app.service;

import com.life_protocol.app.model.Subtask;
import com.life_protocol.app.repository.SubtaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubtaskServiceImpl implements SubtaskService {

    private final SubtaskRepository subtaskRepository;

    @Autowired
    public SubtaskServiceImpl(SubtaskRepository subtaskRepository) {
        this.subtaskRepository = subtaskRepository;
    }

    @Override
    public Subtask createSubtask(Subtask subtask) {
        subtask.setCreatedAt(LocalDateTime.now());
        subtask.setUpdatedAt(LocalDateTime.now());
        return subtaskRepository.save(subtask);
    }

    @Override
    public Optional<Subtask> getSubtaskById(String id) {
        return subtaskRepository.findById(id);
    }

    @Override
    public List<Subtask> getSubtasksByProtocolId(String protocolId) {
        return subtaskRepository.findByProtocolId(protocolId);
    }

    @Override
    public List<Subtask> getCompletedSubtasksByProtocolId(String protocolId) {
        return subtaskRepository.findByProtocolIdAndCompleted(protocolId, true);
    }

    @Override
    public List<Subtask> getIncompleteSubtasksByProtocolId(String protocolId) {
        return subtaskRepository.findByProtocolIdAndCompleted(protocolId, false);
    }

    @Override
    public Subtask updateSubtask(Subtask subtask) {
        subtask.setUpdatedAt(LocalDateTime.now());
        return subtaskRepository.save(subtask);
    }

    @Override
    public void deleteSubtask(String id) {
        subtaskRepository.deleteById(id);
    }

    @Override
    public void completeSubtask(String id) {
        subtaskRepository.findById(id).ifPresent(subtask -> {
            subtask.setCompleted(true);
            subtask.setCompletedAt(LocalDateTime.now());
            subtask.setUpdatedAt(LocalDateTime.now());
            subtaskRepository.save(subtask);
        });
    }
}