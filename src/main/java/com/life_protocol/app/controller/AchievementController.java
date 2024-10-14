package com.life_protocol.app.controller;

import com.life_protocol.app.model.Achievement;
import com.life_protocol.app.service.AchievementService;
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
@RequestMapping("/api/achievements")
@Tag(name = "Achievement Management", description = "Operations pertaining to achievements in the Life Protocol application")
public class AchievementController {

    private final AchievementService achievementService;

    @Autowired
    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @PostMapping
    @Operation(summary = "Create a new achievement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Achievement successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Achievement> createAchievement(@Parameter(description = "Achievement object to be created", required = true) @RequestBody Achievement achievement) {
        Achievement createdAchievement = achievementService.createAchievement(achievement);
        return new ResponseEntity<>(createdAchievement, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an achievement by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved achievement"),
            @ApiResponse(responseCode = "404", description = "Achievement not found")
    })
    public ResponseEntity<Achievement> getAchievementById(@Parameter(description = "ID of the achievement to be retrieved", required = true) @PathVariable String id) {
        return achievementService.getAchievementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all achievements")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of achievements")
    public ResponseEntity<List<Achievement>> getAllAchievements() {
        List<Achievement> achievements = achievementService.getAllAchievements();
        return ResponseEntity.ok(achievements);
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get achievements by category")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of achievements")
    public ResponseEntity<List<Achievement>> getAchievementsByCategory(@Parameter(description = "Category of achievements to retrieve", required = true) @PathVariable String category) {
        List<Achievement> achievements = achievementService.getAchievementsByCategory(category);
        return ResponseEntity.ok(achievements);
    }

    @GetMapping("/experience")
    @Operation(summary = "Get achievements by maximum experience points")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of achievements")
    public ResponseEntity<List<Achievement>> getAchievementsByExperiencePoints(@Parameter(description = "Maximum experience points", required = true) @RequestParam int maxPoints) {
        List<Achievement> achievements = achievementService.getAchievementsByExperiencePoints(maxPoints);
        return ResponseEntity.ok(achievements);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an achievement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Achievement successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Achievement not found")
    })
    public ResponseEntity<Achievement> updateAchievement(
            @Parameter(description = "ID of the achievement to be updated", required = true) @PathVariable String id,
            @Parameter(description = "Updated achievement object", required = true) @RequestBody Achievement achievement) {
        if (!id.equals(achievement.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Achievement updatedAchievement = achievementService.updateAchievement(achievement);
        return ResponseEntity.ok(updatedAchievement);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an achievement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Achievement successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Achievement not found")
    })
    public ResponseEntity<Void> deleteAchievement(@Parameter(description = "ID of the achievement to be deleted", required = true) @PathVariable String id) {
        achievementService.deleteAchievement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}/unlocked")
    @Operation(summary = "Get unlocked achievements for a user")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of unlocked achievements")
    public ResponseEntity<List<Achievement>> getUnlockedAchievements(@Parameter(description = "ID of the user", required = true) @PathVariable String userId) {
        List<Achievement> unlockedAchievements = achievementService.getUnlockedAchievements(userId);
        return ResponseEntity.ok(unlockedAchievements);
    }

    @PostMapping("/{achievementId}/unlock/{userId}")
    @Operation(summary = "Unlock an achievement for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Achievement successfully unlocked"),
            @ApiResponse(responseCode = "404", description = "Achievement or user not found")
    })
    public ResponseEntity<Void> unlockAchievement(
            @Parameter(description = "ID of the achievement to be unlocked", required = true) @PathVariable String achievementId,
            @Parameter(description = "ID of the user", required = true) @PathVariable String userId) {
        achievementService.unlockAchievement(userId, achievementId);
        return ResponseEntity.noContent().build();
    }
}