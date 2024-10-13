package com.life_protocol.app.controller;

import com.life_protocol.app.model.Subtask;
import com.life_protocol.app.service.SubtaskService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subtasks")
@Api(tags = "Subtask Management", description = "Operations pertaining to subtasks in the Life Protocol application")
public class SubtaskController {

    private final SubtaskService subtaskService;

    @Autowired
    public SubtaskController(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
    }

    @PostMapping
    @ApiOperation(value = "Create a new subtask", response = Subtask.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Subtask successfully created"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<Subtask> createSubtask(@ApiParam(value = "Subtask object to be created", required = true) @RequestBody Subtask subtask) {
        Subtask createdSubtask = subtaskService.createSubtask(subtask);
        return new ResponseEntity<>(createdSubtask, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a subtask by ID", response = Subtask.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved subtask"),
            @ApiResponse(code = 404, message = "Subtask not found")
    })
    public ResponseEntity<Subtask> getSubtaskById(@ApiParam(value = "ID of the subtask to be retrieved", required = true) @PathVariable String id) {
        return subtaskService.getSubtaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/protocol/{protocolId}")
    @ApiOperation(value = "Get subtasks by protocol ID", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of subtasks")
    public ResponseEntity<List<Subtask>> getSubtasksByProtocolId(@ApiParam(value = "ID of the protocol", required = true) @PathVariable String protocolId) {
        List<Subtask> subtasks = subtaskService.getSubtasksByProtocolId(protocolId);
        return ResponseEntity.ok(subtasks);
    }

    @GetMapping("/protocol/{protocolId}/completed")
    @ApiOperation(value = "Get completed subtasks by protocol ID", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of completed subtasks")
    public ResponseEntity<List<Subtask>> getCompletedSubtasksByProtocolId(@ApiParam(value = "ID of the protocol", required = true) @PathVariable String protocolId) {
        List<Subtask> completedSubtasks = subtaskService.getCompletedSubtasksByProtocolId(protocolId);
        return ResponseEntity.ok(completedSubtasks);
    }

    @GetMapping("/protocol/{protocolId}/incomplete")
    @ApiOperation(value = "Get incomplete subtasks by protocol ID", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of incomplete subtasks")
    public ResponseEntity<List<Subtask>> getIncompleteSubtasksByProtocolId(@ApiParam(value = "ID of the protocol", required = true) @PathVariable String protocolId) {
        List<Subtask> incompleteSubtasks = subtaskService.getIncompleteSubtasksByProtocolId(protocolId);
        return ResponseEntity.ok(incompleteSubtasks);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a subtask", response = Subtask.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Subtask successfully updated"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "Subtask not found")
    })
    public ResponseEntity<Subtask> updateSubtask(
            @ApiParam(value = "ID of the subtask to be updated", required = true) @PathVariable String id,
            @ApiParam(value = "Updated subtask object", required = true) @RequestBody Subtask subtask) {
        if (!id.equals(subtask.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Subtask updatedSubtask = subtaskService.updateSubtask(subtask);
        return ResponseEntity.ok(updatedSubtask);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a subtask")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Subtask successfully deleted"),
            @ApiResponse(code = 404, message = "Subtask not found")
    })
    public ResponseEntity<Void> deleteSubtask(@ApiParam(value = "ID of the subtask to be deleted", required = true) @PathVariable String id) {
        subtaskService.deleteSubtask(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/complete")
    @ApiOperation(value = "Mark a subtask as complete")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Subtask successfully marked as complete"),
            @ApiResponse(code = 404, message = "Subtask not found")
    })
    public ResponseEntity<Void> completeSubtask(@ApiParam(value = "ID of the subtask to be marked as complete", required = true) @PathVariable String id) {
        subtaskService.completeSubtask(id);
        return ResponseEntity.noContent().build();
    }
}