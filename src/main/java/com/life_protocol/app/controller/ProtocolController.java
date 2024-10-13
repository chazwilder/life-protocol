package com.life_protocol.app.controller;

import com.life_protocol.app.model.Protocol;
import com.life_protocol.app.service.ProtocolService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/protocols")
@Api(tags = "Protocol Management", description = "Operations pertaining to protocols in the Life Protocol application")
public class ProtocolController {

    private final ProtocolService protocolService;

    @Autowired
    public ProtocolController(ProtocolService protocolService) {
        this.protocolService = protocolService;
    }

    @PostMapping
    @ApiOperation(value = "Create a new protocol", response = Protocol.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Protocol successfully created"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<Protocol> createProtocol(@ApiParam(value = "Protocol object to be created", required = true) @RequestBody Protocol protocol) {
        Protocol createdProtocol = protocolService.createProtocol(protocol);
        return new ResponseEntity<>(createdProtocol, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a protocol by ID", response = Protocol.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved protocol"),
            @ApiResponse(code = 404, message = "Protocol not found")
    })
    public ResponseEntity<Protocol> getProtocolById(@ApiParam(value = "ID of the protocol to be retrieved", required = true) @PathVariable String id) {
        return protocolService.getProtocolById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @ApiOperation(value = "Get protocols by user ID", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of protocols")
    public ResponseEntity<List<Protocol>> getProtocolsByUserId(@ApiParam(value = "ID of the user", required = true) @PathVariable String userId) {
        List<Protocol> protocols = protocolService.getProtocolsByUserId(userId);
        return ResponseEntity.ok(protocols);
    }

    @GetMapping("/user/{userId}/active")
    @ApiOperation(value = "Get active protocols by user ID", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of active protocols")
    public ResponseEntity<List<Protocol>> getActiveProtocolsByUserId(@ApiParam(value = "ID of the user", required = true) @PathVariable String userId) {
        List<Protocol> activeProtocols = protocolService.getActiveProtocolsByUserId(userId);
        return ResponseEntity.ok(activeProtocols);
    }

    @GetMapping("/user/{userId}/due")
    @ApiOperation(value = "Get due protocols by user ID", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of due protocols")
    public ResponseEntity<List<Protocol>> getDueProtocolsByUserId(
            @ApiParam(value = "ID of the user", required = true) @PathVariable String userId,
            @ApiParam(value = "Due date and time", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDate) {
        List<Protocol> dueProtocols = protocolService.getDueProtocolsByUserId(userId, dueDate);
        return ResponseEntity.ok(dueProtocols);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a protocol", response = Protocol.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Protocol successfully updated"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "Protocol not found")
    })
    public ResponseEntity<Protocol> updateProtocol(
            @ApiParam(value = "ID of the protocol to be updated", required = true) @PathVariable String id,
            @ApiParam(value = "Updated protocol object", required = true) @RequestBody Protocol protocol) {
        if (!id.equals(protocol.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Protocol updatedProtocol = protocolService.updateProtocol(protocol);
        return ResponseEntity.ok(updatedProtocol);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a protocol")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Protocol successfully deleted"),
            @ApiResponse(code = 404, message = "Protocol not found")
    })
    public ResponseEntity<Void> deleteProtocol(@ApiParam(value = "ID of the protocol to be deleted", required = true) @PathVariable String id) {
        protocolService.deleteProtocol(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/goal/{goalId}")
    @ApiOperation(value = "Get protocols by goal ID", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of protocols")
    public ResponseEntity<List<Protocol>> getProtocolsByGoalId(@ApiParam(value = "ID of the goal", required = true) @PathVariable String goalId) {
        List<Protocol> protocols = protocolService.getProtocolsByGoalId(goalId);
        return ResponseEntity.ok(protocols);
    }
}