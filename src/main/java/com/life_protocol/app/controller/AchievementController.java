package com.life_protocol.app.controller;

import com.life_protocol.app.model.Achievement;
import com.life_protocol.app.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/achievements")
public class AchievementController {

    private final AchievementService achievementService;

    @Autowired
    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @PostMapping
    public ResponseEntity<Achievement> createAchievement(@RequestBody Achievement achievement) {
        Achievement createdAchievement = achievementService.createAchievement(achievement);
        return new ResponseEntity<>(createdAchievement, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Achievement> getAchievementById(@PathVariable String id) {
        return achievementService.getAchievementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Achievement>> getAllAchievements() {
        List<Achievement> achievements = achievementService.getAllAchievements();
        return ResponseEntity.ok(achievements);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Achievement>> getAchievementsByCategory(@PathVariable String category) {
        List<Achievement> achievements = achievementService.getAchievementsByCategory(category);
        return ResponseEntity.ok(achievements);
    }

    @GetMapping("/experience")
    public ResponseEntity<List<Achievement>> getAchievementsByExperiencePoints(@RequestParam int maxPoints) {
        List<Achievement> achievements = achievementService.getAchievementsByExperiencePoints(maxPoints);
        return ResponseEntity.ok(achievements);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Achievement> updateAchievement(@PathVariable String id, @RequestBody Achievement achievement) {
        if (!id.equals(achievement.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Achievement updatedAchievement = achievementService.updateAchievement(achievement);
        return ResponseEntity.ok(updatedAchievement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAchievement(@PathVariable String id) {
        achievementService.deleteAchievement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}/unlocked")
    public ResponseEntity<List<Achievement>> getUnlockedAchievements(@PathVariable String userId) {
        List<Achievement> unlockedAchievements = achievementService.getUnlockedAchievements(userId);
        return ResponseEntity.ok(unlockedAchievements);
    }

    @PostMapping("/{achievementId}/unlock/{userId}")
    public ResponseEntity<Void> unlockAchievement(@PathVariable String achievementId, @PathVariable String userId) {
        achievementService.unlockAchievement(userId, achievementId);
        return ResponseEntity.noContent().build();
    }
}