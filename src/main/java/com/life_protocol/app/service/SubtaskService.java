package com.life_protocol.app.service;

import com.life_protocol.app.model.Subtask;
import java.util.List;
import java.util.Optional;

public interface SubtaskService {
    Subtask createSubtask(Subtask subtask);
    Optional<Subtask> getSubtaskById(String id);
    List<Subtask> getSubtasksByProtocolId(String protocolId);
    List<Subtask> getCompletedSubtasksByProtocolId(String protocolId);
    List<Subtask> getIncompleteSubtasksByProtocolId(String protocolId);
    Subtask updateSubtask(Subtask subtask);
    void deleteSubtask(String id);
    void completeSubtask(String id);
}