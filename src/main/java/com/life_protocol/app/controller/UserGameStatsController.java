package com.life_protocol.app.controller;

import com.life_protocol.app.model.UserGameStats;
import com.life_protocol.app.service.UserGameStatsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-game-stats")
@Api(tags = "User Game Stats Management", description = "Operations pertaining to user game statistics in the Life Protocol application")
public class UserGameStatsController {

    private final UserGameStatsService userGameStatsService;

    @Autowired
    public UserGameStatsController(UserGameStatsService userGameStatsService) {
        this.userGameStatsService = userGameStatsService;
    }

    @PostMapping
    @ApiOperation(value = "Create new user game stats", response = UserGameStats.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User game stats successfully created"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<UserGameStats> createUserGameStats(@ApiParam(value = "User game stats object to be created", required = true) @RequestBody UserGameStats userGameStats) {
        UserGameStats createdStats = userGameStatsService.createUserGameStats(userGameStats);
        return new ResponseEntity<>(createdStats, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user game stats by ID", response = UserGameStats.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user game stats"),
            @ApiResponse(code = 404, message = "User game stats not found")
    })
    public ResponseEntity<UserGameStats> getUserGameStatsById(@ApiParam(value = "ID of the user game stats to be retrieved", required = true) @PathVariable String id) {
        return userGameStatsService.getUserGameStatsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @ApiOperation(value = "Get user game stats by user ID", response = UserGameStats.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user game stats"),
            @ApiResponse(code = 404, message = "User game stats not found")
    })
    public ResponseEntity<UserGameStats> getUserGameStatsByUserId(@ApiParam(value = "ID of the user", required = true) @PathVariable String userId) {
        return userGameStatsService.getUserGameStatsByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/top/level")
    @ApiOperation(value = "Get top users by level", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of top users by level")
    public ResponseEntity<List<UserGameStats>> getTopUsersByLevel(@ApiParam(value = "Limit of users to retrieve", defaultValue = "10") @RequestParam(defaultValue = "10") int limit) {
        List<UserGameStats> topUsers = userGameStatsService.getTopUsersByLevel(limit);
        return ResponseEntity.ok(topUsers);
    }

    @GetMapping("/top/xp")
    @ApiOperation(value = "Get top users by experience points", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of top users by experience points")
    public ResponseEntity<List<UserGameStats>> getTopUsersByExperiencePoints(@ApiParam(value = "Limit of users to retrieve", defaultValue = "10") @RequestParam(defaultValue = "10") int limit) {
        List<UserGameStats> topUsers = userGameStatsService.getTopUsersByExperiencePoints(limit);
        return ResponseEntity.ok(topUsers);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update user game stats", response = UserGameStats.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User game stats successfully updated"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "User game stats not found")
    })
    public ResponseEntity<UserGameStats> updateUserGameStats(
            @ApiParam(value = "ID of the user game stats to be updated", required = true) @PathVariable String id,
            @ApiParam(value = "Updated user game stats object", required = true) @RequestBody UserGameStats userGameStats) {
        if (!id.equals(userGameStats.getId())) {
            return ResponseEntity.badRequest().build();
        }
        UserGameStats updatedStats = userGameStatsService.updateUserGameStats(userGameStats);
        return ResponseEntity.ok(updatedStats);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete user game stats")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "User game stats successfully deleted"),
            @ApiResponse(code = 404, message = "User game stats not found")
    })
    public ResponseEntity<Void> deleteUserGameStats(@ApiParam(value = "ID of the user game stats to be deleted", required = true) @PathVariable String id) {
        userGameStatsService.deleteUserGameStats(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/add-xp")
    @ApiOperation(value = "Add experience points to a user")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Experience points successfully added"),
            @ApiResponse(code = 404, message = "User game stats not found")
    })
    public ResponseEntity<Void> addExperiencePoints(
            @ApiParam(value = "ID of the user", required = true) @PathVariable String userId,
            @ApiParam(value = "Experience points to add", required = true) @RequestParam int points) {
        userGameStatsService.addExperiencePoints(userId, points);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/update-hp")
    @ApiOperation(value = "Update health points of a user")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Health points successfully updated"),
            @ApiResponse(code = 404, message = "User game stats not found")
    })
    public ResponseEntity<Void> updateHealthPoints(
            @ApiParam(value = "ID of the user", required = true) @PathVariable String userId,
            @ApiParam(value = "New health points value", required = true) @RequestParam int healthPoints) {
        userGameStatsService.updateHealthPoints(userId, healthPoints);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/increment-streak")
    @ApiOperation(value = "Increment streak for a user")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Streak successfully incremented"),
            @ApiResponse(code = 404, message = "User game stats not found")
    })
    public ResponseEntity<Void> incrementStreak(@ApiParam(value = "ID of the user", required = true) @PathVariable String userId) {
        userGameStatsService.incrementStreak(userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/reset-streak")
    @ApiOperation(value = "Reset streak for a user")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Streak successfully reset"),
            @ApiResponse(code = 404, message = "User game stats not found")
    })
    public ResponseEntity<Void> resetStreak(@ApiParam(value = "ID of the user", required = true) @PathVariable String userId) {
        userGameStatsService.resetStreak(userId);
        return ResponseEntity.noContent().build();
    }
}