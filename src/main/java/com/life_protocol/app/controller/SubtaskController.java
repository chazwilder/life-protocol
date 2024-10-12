package com.life_protocol.app.controller;

import com.life_protocol.app.model.Subtask;
import com.life_protocol.app.service.SubtaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subtasks")
public class SubtaskController {

    private final SubtaskService subtaskService;

    @Autowired
    public SubtaskController(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
    }

    @PostMapping
    public ResponseEntity<Subtask> createSubtask(@RequestBody Subtask subtask) {
        Subtask createdSubtask = subtaskService.createSubtask(subtask);
        return new ResponseEntity<>(createdSubtask, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subtask> getSubtaskById(@PathVariable String id) {
        return subtaskService.getSubtaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/protocol/{protocolId}")
    public ResponseEntity<List<Subtask>> getSubtasksByProtocolId(@PathVariable String protocolId) {
        List<Subtask> subtasks = subtaskService.getSubtasksByProtocolId(protocolId);
        return ResponseEntity.ok(subtasks);
    }

    @GetMapping("/protocol/{protocolId}/completed")
    public ResponseEntity<List<Subtask>> getCompletedSubtasksByProtocolId(@PathVariable String protocolId) {
        List<Subtask> completedSubtasks = subtaskService.getCompletedSubtasksByProtocolId(protocolId);
        return ResponseEntity.ok(completedSubtasks);
    }

    @GetMapping("/protocol/{protocolId}/incomplete")
    public ResponseEntity<List<Subtask>> getIncompleteSubtasksByProtocolId(@PathVariable String protocolId) {
        List<Subtask> incompleteSubtasks = subtaskService.getIncompleteSubtasksByProtocolId(protocolId);
        return ResponseEntity.ok(incompleteSubtasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subtask> updateSubtask(@PathVariable String id, @RequestBody Subtask subtask) {
        if (!id.equals(subtask.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Subtask updatedSubtask = subtaskService.updateSubtask(subtask);
        return ResponseEntity.ok(updatedSubtask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubtask(@PathVariable String id) {
        subtaskService.deleteSubtask(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Void> completeSubtask(@PathVariable String id) {
        subtaskService.completeSubtask(id);
        return ResponseEntity.noContent().build();
    }
}