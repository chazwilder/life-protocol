package com.life_protocol.app.controller;

import com.life_protocol.app.model.UserGameStats;
import com.life_protocol.app.service.UserGameStatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-game-stats")
@Tag(name = "User Game Stats Management", description = "Operations pertaining to user game statistics in the Life Protocol application")
public class UserGameStatsController {

    private final UserGameStatsService userGameStatsService;

    @Autowired
    public UserGameStatsController(UserGameStatsService userGameStatsService) {
        this.userGameStatsService = userGameStatsService;
    }

    @PostMapping
    @Operation(summary = "Create new user game stats")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User game stats successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<UserGameStats> createUserGameStats(@Parameter(description = "User game stats object to be created", required = true) @RequestBody UserGameStats userGameStats) {
        UserGameStats createdStats = userGameStatsService.createUserGameStats(userGameStats);
        return new ResponseEntity<>(createdStats, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user game stats by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user game stats"),
            @ApiResponse(responseCode = "404", description = "User game stats not found")
    })
    public ResponseEntity<UserGameStats> getUserGameStatsById(@Parameter(description = "ID of the user game stats to be retrieved", required = true) @PathVariable String id) {
        return userGameStatsService.getUserGameStatsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user game stats by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user game stats"),
            @ApiResponse(responseCode = "404", description = "User game stats not found")
    })
    public ResponseEntity<UserGameStats> getUserGameStatsByUserId(@Parameter(description = "ID of the user", required = true) @PathVariable String userId) {
        return userGameStatsService.getUserGameStatsByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/top/level")
    @Operation(summary = "Get top users by level")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of top users by level")
    public ResponseEntity<List<UserGameStats>> getTopUsersByLevel(@Parameter(description = "Limit of users to retrieve", required = false) @RequestParam(defaultValue = "10") int limit) {
        List<UserGameStats> topUsers = userGameStatsService.getTopUsersByLevel(limit);
        return ResponseEntity.ok(topUsers);
    }

    @GetMapping("/top/xp")
    @Operation(summary = "Get top users by experience points")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of top users by experience points")
    public ResponseEntity<List<UserGameStats>> getTopUsersByExperiencePoints(@Parameter(description = "Limit of users to retrieve", required = false) @RequestParam(defaultValue = "10") int limit) {
        List<UserGameStats> topUsers = userGameStatsService.getTopUsersByExperiencePoints(limit);
        return ResponseEntity.ok(topUsers);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user game stats")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User game stats successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "User game stats not found")
    })
    public ResponseEntity<UserGameStats> updateUserGameStats(
            @Parameter(description = "ID of the user game stats to be updated", required = true) @PathVariable String id,
            @Parameter(description = "Updated user game stats object", required = true) @RequestBody UserGameStats userGameStats) {
        if (!id.equals(userGameStats.getId())) {
            return ResponseEntity.badRequest().build();
        }
        UserGameStats updatedStats = userGameStatsService.updateUserGameStats(userGameStats);
        return ResponseEntity.ok(updatedStats);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user game stats")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User game stats successfully deleted"),
            @ApiResponse(responseCode = "404", description = "User game stats not found")
    })
    public ResponseEntity<Void> deleteUserGameStats(@Parameter(description = "ID of the user game stats to be deleted", required = true) @PathVariable String id) {
        userGameStatsService.deleteUserGameStats(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/add-xp")
    @Operation(summary = "Add experience points to a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Experience points successfully added"),
            @ApiResponse(responseCode = "404", description = "User game stats not found")
    })
    public ResponseEntity<Void> addExperiencePoints(
            @Parameter(description = "ID of the user", required = true) @PathVariable String userId,
            @Parameter(description = "Experience points to add", required = true) @RequestParam int points) {
        userGameStatsService.addExperiencePoints(userId, points);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/update-hp")
    @Operation(summary = "Update health points of a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Health points successfully updated"),
            @ApiResponse(responseCode = "404", description = "User game stats not found")
    })
    public ResponseEntity<Void> updateHealthPoints(
            @Parameter(description = "ID of the user", required = true) @PathVariable String userId,
            @Parameter(description = "New health points value", required = true) @RequestParam int healthPoints) {
        userGameStatsService.updateHealthPoints(userId, healthPoints);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/increment-streak")
    @Operation(summary = "Increment streak for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Streak successfully incremented"),
            @ApiResponse(responseCode = "404", description = "User game stats not found")
    })
    public ResponseEntity<Void> incrementStreak(@Parameter(description = "ID of the user", required = true) @PathVariable String userId) {
        userGameStatsService.incrementStreak(userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/reset-streak")
    @Operation(summary = "Reset streak for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Streak successfully reset"),
            @ApiResponse(responseCode = "404", description = "User game stats not found")
    })
    public ResponseEntity<Void> resetStreak(@Parameter(description = "ID of the user", required = true) @PathVariable String userId) {
        userGameStatsService.resetStreak(userId);
        return ResponseEntity.noContent().build();
    }
}