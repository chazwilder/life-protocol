package com.life_protocol.app.service;

import com.life_protocol.app.model.UserConnection;
import java.util.List;
import java.util.Optional;

public interface UserConnectionService {
    UserConnection createUserConnection(UserConnection userConnection);
    Optional<UserConnection> getUserConnectionById(String id);
    List<UserConnection> getUserConnectionsByUserId(String userId);
    List<UserConnection> getUserConnectionsByConnectedUserId(String connectedUserId);
    List<UserConnection> getUserConnectionsByUserIdAndConnectionType(String userId, UserConnection.ConnectionType connectionType);
    List<UserConnection> getUserConnectionsByUserIdAndStatus(String userId, UserConnection.ConnectionStatus status);
    Optional<UserConnection> getUserConnectionByUserIdAndConnectedUserId(String userId, String connectedUserId);
    UserConnection updateUserConnection(UserConnection userConnection);
    void deleteUserConnection(String id);
}