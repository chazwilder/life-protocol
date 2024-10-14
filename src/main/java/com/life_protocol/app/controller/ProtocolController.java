package com.life_protocol.app.controller;

import com.life_protocol.app.model.Protocol;
import com.life_protocol.app.service.ProtocolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/protocols")
@Tag(name = "Protocol Management", description = "Operations pertaining to protocols in the Life Protocol application")
public class ProtocolController {

    private final ProtocolService protocolService;

    @Autowired
    public ProtocolController(ProtocolService protocolService) {
        this.protocolService = protocolService;
    }

    @PostMapping
    @Operation(summary = "Create a new protocol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Protocol successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Protocol> createProtocol(@Parameter(description = "Protocol object to be created", required = true) @RequestBody Protocol protocol) {
        Protocol createdProtocol = protocolService.createProtocol(protocol);
        return new ResponseEntity<>(createdProtocol, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a protocol by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved protocol"),
            @ApiResponse(responseCode = "404", description = "Protocol not found")
    })
    public ResponseEntity<Protocol> getProtocolById(@Parameter(description = "ID of the protocol to be retrieved", required = true) @PathVariable String id) {
        return protocolService.getProtocolById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get protocols by user ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of protocols")
    public ResponseEntity<List<Protocol>> getProtocolsByUserId(@Parameter(description = "ID of the user", required = true) @PathVariable String userId) {
        List<Protocol> protocols = protocolService.getProtocolsByUserId(userId);
        return ResponseEntity.ok(protocols);
    }

    @GetMapping("/user/{userId}/active")
    @Operation(summary = "Get active protocols by user ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of active protocols")
    public ResponseEntity<List<Protocol>> getActiveProtocolsByUserId(@Parameter(description = "ID of the user", required = true) @PathVariable String userId) {
        List<Protocol> activeProtocols = protocolService.getActiveProtocolsByUserId(userId);
        return ResponseEntity.ok(activeProtocols);
    }

    @GetMapping("/user/{userId}/due")
    @Operation(summary = "Get due protocols by user ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of due protocols")
    public ResponseEntity<List<Protocol>> getDueProtocolsByUserId(
            @Parameter(description = "ID of the user", required = true) @PathVariable String userId,
            @Parameter(description = "Due date and time", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDate) {
        List<Protocol> dueProtocols = protocolService.getDueProtocolsByUserId(userId, dueDate);
        return ResponseEntity.ok(dueProtocols);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a protocol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Protocol successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Protocol not found")
    })
    public ResponseEntity<Protocol> updateProtocol(
            @Parameter(description = "ID of the protocol to be updated", required = true) @PathVariable String id,
            @Parameter(description = "Updated protocol object", required = true) @RequestBody Protocol protocol) {
        if (!id.equals(protocol.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Protocol updatedProtocol = protocolService.updateProtocol(protocol);
        return ResponseEntity.ok(updatedProtocol);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a protocol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Protocol successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Protocol not found")
    })
    public ResponseEntity<Void> deleteProtocol(@Parameter(description = "ID of the protocol to be deleted", required = true) @PathVariable String id) {
        protocolService.deleteProtocol(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/goal/{goalId}")
    @Operation(summary = "Get protocols by goal ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of protocols")
    public ResponseEntity<List<Protocol>> getProtocolsByGoalId(@Parameter(description = "ID of the goal", required = true) @PathVariable String goalId) {
        List<Protocol> protocols = protocolService.getProtocolsByGoalId(goalId);
        return ResponseEntity.ok(protocols);
    }
}