package com.life_protocol.app.service;

import com.life_protocol.app.model.UserConnection;
import com.life_protocol.app.repository.UserConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserConnectionServiceImpl implements UserConnectionService {

    private final UserConnectionRepository userConnectionRepository;

    @Autowired
    public UserConnectionServiceImpl(UserConnectionRepository userConnectionRepository) {
        this.userConnectionRepository = userConnectionRepository;
    }

    @Override
    public UserConnection createUserConnection(UserConnection userConnection) {
        return userConnectionRepository.save(userConnection);
    }

    @Override
    public Optional<UserConnection> getUserConnectionById(String id) {
        return userConnectionRepository.findById(id);
    }

    @Override
    public List<UserConnection> getUserConnectionsByUserId(String userId) {
        return userConnectionRepository.findByUserId(userId);
    }

    @Override
    public List<UserConnection> getUserConnectionsByConnectedUserId(String connectedUserId) {
        return userConnectionRepository.findByConnectedUserId(connectedUserId);
    }

    @Override
    public List<UserConnection> getUserConnectionsByUserIdAndConnectionType(String userId, UserConnection.ConnectionType connectionType) {
        return userConnectionRepository.findByUserIdAndConnectionType(userId, connectionType);
    }

    @Override
    public List<UserConnection> getUserConnectionsByUserIdAndStatus(String userId, UserConnection.ConnectionStatus status) {
        return userConnectionRepository.findByUserIdAndStatus(userId, status);
    }

    @Override
    public Optional<UserConnection> getUserConnectionByUserIdAndConnectedUserId(String userId, String connectedUserId) {
        return Optional.ofNullable(userConnectionRepository.findByUserIdAndConnectedUserId(userId, connectedUserId));
    }

    @Override
    public UserConnection updateUserConnection(UserConnection userConnection) {
        return userConnectionRepository.save(userConnection);
    }

    @Override
    public void deleteUserConnection(String id) {
        userConnectionRepository.deleteById(id);
    }
}