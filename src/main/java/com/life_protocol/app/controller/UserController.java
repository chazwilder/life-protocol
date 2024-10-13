package com.life_protocol.app.controller;

import com.life_protocol.app.model.User;
import com.life_protocol.app.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api(tags = "User Management", description = "Operations pertaining to users in the Life Protocol application")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation(value = "Create a new user", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User successfully created"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<User> createUser(@ApiParam(value = "User object to be created", required = true) @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a user by ID", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<User> getUserById(@ApiParam(value = "ID of the user to be retrieved", required = true) @PathVariable String id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @ApiOperation(value = "Get all users", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a user", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User successfully updated"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<User> updateUser(
            @ApiParam(value = "ID of the user to be updated", required = true) @PathVariable String id,
            @ApiParam(value = "Updated user object", required = true) @RequestBody User user) {
        if (!id.equals(user.getId())) {
            return ResponseEntity.badRequest().build();
        }
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "User successfully deleted"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<Void> deleteUser(@ApiParam(value = "ID of the user to be deleted", required = true) @PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/username/{username}")
    @ApiOperation(value = "Get a user by username", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<User> getUserByUsername(@ApiParam(value = "Username of the user to be retrieved", required = true) @PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    @ApiOperation(value = "Get a user by email", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<User> getUserByEmail(@ApiParam(value = "Email of the user to be retrieved", required = true) @PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}