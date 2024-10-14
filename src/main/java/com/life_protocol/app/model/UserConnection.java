package com.life_protocol.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "user_connections")
public class UserConnection {
    @Id
    private String id;

    private String userId;
    private String connectedUserId;
    private ConnectionType connectionType;
    private ConnectionStatus status;
    private LocalDateTime createdAt;

    public enum ConnectionType {
        FRIEND, ACCOUNTABILITY_PARTNER
    }

    public enum ConnectionStatus {
        PENDING, ACCEPTED, DECLINED
    }

    public UserConnection() {
    }

    public UserConnection(String userId, String connectedUserId, ConnectionType connectionType) {
        this.userId = userId;
        this.connectedUserId = connectedUserId;
        this.connectionType = connectionType;
        this.status = ConnectionStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getConnectedUserId() {
        return connectedUserId;
    }

    public void setConnectedUserId(String connectedUserId) {
        this.connectedUserId = connectedUserId;
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public ConnectionStatus getStatus() {
        return status;
    }

    public void setStatus(ConnectionStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}