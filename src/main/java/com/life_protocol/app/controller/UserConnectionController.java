package com.life_protocol.app.controller;

import com.life_protocol.app.model.UserConnection;
import com.life_protocol.app.service.UserConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-connections")
public class UserConnectionController {

    private final UserConnectionService userConnectionService;

    @Autowired
    public UserConnectionController(UserConnectionService userConnectionService) {
        this.userConnectionService = userConnectionService;
    }

    @PostMapping
    public ResponseEntity<UserConnection> createUserConnection(@RequestBody UserConnection userConnection) {
        UserConnection createdConnection = userConnectionService.createUserConnection(userConnection);
        return new ResponseEntity<>(createdConnection, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserConnection> getUserConnectionById(@PathVariable String id) {
        return userConnectionService.getUserConnectionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserConnection>> getUserConnectionsByUserId(@PathVariable String userId) {
        List<UserConnection> connections = userConnectionService.getUserConnectionsByUserId(userId);
        return ResponseEntity.ok(connections);
    }

    @GetMapping("/connected-user/{connectedUserId}")
    public ResponseEntity<List<UserConnection>> getUserConnectionsByConnectedUserId(@PathVariable String connectedUserId) {
        List<UserConnection> connections = userConnectionService.getUserConnectionsByConnectedUserId(connectedUserId);
        return ResponseEntity.ok(connections);
    }

    @GetMapping("/user/{userId}/type/{connectionType}")
    public ResponseEntity<List<UserConnection>> getUserConnectionsByUserIdAndConnectionType(
            @PathVariable String userId,
            @PathVariable UserConnection.ConnectionType connectionType) {
        List<UserConnection> connections = userConnectionService.getUserConnectionsByUserIdAndConnectionType(userId, connectionType);
        return ResponseEntity.ok(connections);
    }

    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<UserConnection>> getUserConnectionsByUserIdAndStatus(
            @PathVariable String userId,
            @PathVariable UserConnection.ConnectionStatus status) {
        List<UserConnection> connections = userConnectionService.getUserConnectionsByUserIdAndStatus(userId, status);
        return ResponseEntity.ok(connections);
    }

    @GetMapping("/user/{userId}/connected-user/{connectedUserId}")
    public ResponseEntity<UserConnection> getUserConnectionByUserIdAndConnectedUserId(
            @PathVariable String userId,
            @PathVariable String connectedUserId) {
        return userConnectionService.getUserConnectionByUserIdAndConnectedUserId(userId, connectedUserId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserConnection> updateUserConnection(@PathVariable String id, @RequestBody UserConnection userConnection) {
        userConnection.setId(id);
        UserConnection updatedConnection = userConnectionService.updateUserConnection(userConnection);
        return ResponseEntity.ok(updatedConnection);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserConnection(@PathVariable String id) {
        userConnectionService.deleteUserConnection(id);
        return ResponseEntity.noContent().build();
    }
}