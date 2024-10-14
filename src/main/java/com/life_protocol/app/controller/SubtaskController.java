package com.life_protocol.app.controller;

import com.life_protocol.app.model.Subtask;
import com.life_protocol.app.service.SubtaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subtasks")
@Tag(name = "Subtask Management", description = "Operations pertaining to subtasks in the Life Protocol application")
public class SubtaskController {

    private final SubtaskService subtaskService;

    @Autowired
    public SubtaskController(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
    }

    @PostMapping
    @Operation(summary = "Create a new subtask")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subtask successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Subtask> createSubtask(@Parameter(description = "Subtask object to be created", required = true) @RequestBody Subtask subtask) {
        Subtask createdSubtask = subtaskService.createSubtask(subtask);
        return new ResponseEntity<>(createdSubtask, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a subtask by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved subtask"),
            @ApiResponse(responseCode = "404", description = "Subtask not found")
    })
    public ResponseEntity<Subtask> getSubtaskById(@Parameter(description = "ID of the subtask to be retrieved", required = true) @PathVariable String id) {
        return subtaskService.getSubtaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/protocol/{protocolId}")
    @Operation(summary = "Get subtasks by protocol ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of subtasks")
    public ResponseEntity<List<Subtask>> getSubtasksByProtocolId(@Parameter(description = "ID of the protocol", required = true) @PathVariable String protocolId) {
        List<Subtask> subtasks = subtaskService.getSubtasksByProtocolId(protocolId);
        return ResponseEntity.ok(subtasks);
    }

    @GetMapping("/protocol/{protocolId}/completed")
    @Operation(summary = "Get completed subtasks by protocol ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of completed subtasks")
    public ResponseEntity<List<Subtask>> getCompletedSubtasksByProtocolId(@Parameter(description = "ID of the protocol", required = true) @PathVariable String protocolId) {
        List<Subtask> completedSubtasks = subtaskService.getCompletedSubtasksByProtocolId(protocolId);
        return ResponseEntity.ok(completedSubtasks);
    }

    @GetMapping("/protocol/{protocolId}/incomplete")
    @Operation(summary = "Get incomplete subtasks by protocol ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of incomplete subtasks")
    public ResponseEntity<List<Subtask>> getIncompleteSubtasksByProtocolId(@Parameter(description = "ID of the protocol", required = true) @PathVariable String protocolId) {
        List<Subtask> incompleteSubtasks = subtaskService.getIncompleteSubtasksByProtocolId(protocolId);
        return ResponseEntity.ok(incompleteSubtasks);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a subtask")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subtask successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Subtask not found")
    })
    public ResponseEntity<Subtask> updateSubtask(
            @Parameter(description = "ID of the subtask to be updated", required = true) @PathVariable String id,
            @Parameter(description = "Updated subtask object", required = true) @RequestBody Subtask subtask) {
        if (!id.equals(subtask.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Subtask updatedSubtask = subtaskService.updateSubtask(subtask);
        return ResponseEntity.ok(updatedSubtask);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a subtask")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Subtask successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Subtask not found")
    })
    public ResponseEntity<Void> deleteSubtask(@Parameter(description = "ID of the subtask to be deleted", required = true) @PathVariable String id) {
        subtaskService.deleteSubtask(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/complete")
    @Operation(summary = "Mark a subtask as complete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Subtask successfully marked as complete"),
            @ApiResponse(responseCode = "404", description = "Subtask not found")
    })
    public ResponseEntity<Void> completeSubtask(@Parameter(description = "ID of the subtask to be marked as complete", required = true) @PathVariable String id) {
        subtaskService.completeSubtask(id);
        return ResponseEntity.noContent().build();
    }
}