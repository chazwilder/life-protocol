package com.life_protocol.app.controller;

import com.life_protocol.app.model.Protocol;
import com.life_protocol.app.service.ProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/protocols")
public class ProtocolController {

    private final ProtocolService protocolService;

    @Autowired
    public ProtocolController(ProtocolService protocolService) {
        this.protocolService = protocolService;
    }

    @PostMapping
    public ResponseEntity<Protocol> createProtocol(@RequestBody Protocol protocol) {
        Protocol createdProtocol = protocolService.createProtocol(protocol);
        return new ResponseEntity<>(createdProtocol, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Protocol> getProtocolById(@PathVariable String id) {
        return protocolService.getProtocolById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Protocol>> getProtocolsByUserId(@PathVariable String userId) {
        List<Protocol> protocols = protocolService.getProtocolsByUserId(userId);
        return ResponseEntity.ok(protocols);
    }

    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<Protocol>> getActiveProtocolsByUserId(@PathVariable String userId) {
        List<Protocol> activeProtocols = protocolService.getActiveProtocolsByUserId(userId);
        return ResponseEntity.ok(activeProtocols);
    }

    @GetMapping("/user/{userId}/due")
    public ResponseEntity<List<Protocol>> getDueProtocolsByUserId(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDate) {
        List<Protocol> dueProtocols = protocolService.getDueProtocolsByUserId(userId, dueDate);
        return ResponseEntity.ok(dueProtocols);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Protocol> updateProtocol(@PathVariable String id, @RequestBody Protocol protocol) {
        if (!id.equals(protocol.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Protocol updatedProtocol = protocolService.updateProtocol(protocol);
        return ResponseEntity.ok(updatedProtocol);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProtocol(@PathVariable String id) {
        protocolService.deleteProtocol(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/goal/{goalId}")
    public ResponseEntity<List<Protocol>> getProtocolsByGoalId(@PathVariable String goalId) {
        List<Protocol> protocols = protocolService.getProtocolsByGoalId(goalId);
        return ResponseEntity.ok(protocols);
    }
}