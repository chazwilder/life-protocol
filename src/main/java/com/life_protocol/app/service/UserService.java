package com.life_protocol.app.service;

import com.life_protocol.app.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(String id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}