package com.life_protocol.app.repository;

import com.life_protocol.app.model.UserConnection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserConnectionRepository extends MongoRepository<UserConnection, String> {
    List<UserConnection> findByUserId(String userId);
    List<UserConnection> findByConnectedUserId(String connectedUserId);
    List<UserConnection> findByUserIdAndConnectionType(String userId, UserConnection.ConnectionType connectionType);
    List<UserConnection> findByUserIdAndStatus(String userId, UserConnection.ConnectionStatus status);
    UserConnection findByUserIdAndConnectedUserId(String userId, String connectedUserId);
}