package com.life_protocol.app.controller;

import com.life_protocol.app.model.UserGameStats;
import com.life_protocol.app.service.UserGameStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-game-stats")
public class UserGameStatsController {

    private final UserGameStatsService userGameStatsService;

    @Autowired
    public UserGameStatsController(UserGameStatsService userGameStatsService) {
        this.userGameStatsService = userGameStatsService;
    }

    @PostMapping
    public ResponseEntity<UserGameStats> createUserGameStats(@RequestBody UserGameStats userGameStats) {
        UserGameStats createdStats = userGameStatsService.createUserGameStats(userGameStats);
        return new ResponseEntity<>(createdStats, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGameStats> getUserGameStatsById(@PathVariable String id) {
        return userGameStatsService.getUserGameStatsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserGameStats> getUserGameStatsByUserId(@PathVariable String userId) {
        return userGameStatsService.getUserGameStatsByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/top/level")
    public ResponseEntity<List<UserGameStats>> getTopUsersByLevel(@RequestParam(defaultValue = "10") int limit) {
        List<UserGameStats> topUsers = userGameStatsService.getTopUsersByLevel(limit);
        return ResponseEntity.ok(topUsers);
    }

    @GetMapping("/top/xp")
    public ResponseEntity<List<UserGameStats>> getTopUsersByExperiencePoints(@RequestParam(defaultValue = "10") int limit) {
        List<UserGameStats> topUsers = userGameStatsService.getTopUsersByExperiencePoints(limit);
        return ResponseEntity.ok(topUsers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserGameStats> updateUserGameStats(@PathVariable String id, @RequestBody UserGameStats userGameStats) {
        if (!id.equals(userGameStats.getId())) {
            return ResponseEntity.badRequest().build();
        }
        UserGameStats updatedStats = userGameStatsService.updateUserGameStats(userGameStats);
        return ResponseEntity.ok(updatedStats);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserGameStats(@PathVariable String id) {
        userGameStatsService.deleteUserGameStats(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/add-xp")
    public ResponseEntity<Void> addExperiencePoints(@PathVariable String userId, @RequestParam int points) {
        userGameStatsService.addExperiencePoints(userId, points);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/update-hp")
    public ResponseEntity<Void> updateHealthPoints(@PathVariable String userId, @RequestParam int healthPoints) {
        userGameStatsService.updateHealthPoints(userId, healthPoints);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/increment-streak")
    public ResponseEntity<Void> incrementStreak(@PathVariable String userId) {
        userGameStatsService.incrementStreak(userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/reset-streak")
    public ResponseEntity<Void> resetStreak(@PathVariable String userId) {
        userGameStatsService.resetStreak(userId);
        return ResponseEntity.noContent().build();
    }
}