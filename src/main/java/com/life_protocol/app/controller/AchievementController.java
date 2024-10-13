package com.life_protocol.app.controller;

import com.life_protocol.app.model.Achievement;
import com.life_protocol.app.service.AchievementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/achievements")
@Api(tags = "Achievement Management", description = "Operations pertaining to achievements in the Life Protocol application")
public class AchievementController {

    private final AchievementService achievementService;

    @Autowired
    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @PostMapping
    @ApiOperation(value = "Create a new achievement", response = Achievement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Achievement successfully created"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<Achievement> createAchievement(@ApiParam(value = "Achievement object to be created", required = true) @RequestBody Achievement achievement) {
        Achievement createdAchievement = achievementService.createAchievement(achievement);
        return new ResponseEntity<>(createdAchievement, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get an achievement by ID", response = Achievement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved achievement"),
            @ApiResponse(code = 404, message = "Achievement not found")
    })
    public ResponseEntity<Achievement> getAchievementById(@ApiParam(value = "ID of the achievement to be retrieved", required = true) @PathVariable String id) {
        return achievementService.getAchievementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @ApiOperation(value = "Get all achievements", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of achievements")
    public ResponseEntity<List<Achievement>> getAllAchievements() {
        List<Achievement> achievements = achievementService.getAllAchievements();
        return ResponseEntity.ok(achievements);
    }

    @GetMapping("/category/{category}")
    @ApiOperation(value = "Get achievements by category", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of achievements")
    public ResponseEntity<List<Achievement>> getAchievementsByCategory(@ApiParam(value = "Category of achievements to retrieve", required = true) @PathVariable String category) {
        List<Achievement> achievements = achievementService.getAchievementsByCategory(category);
        return ResponseEntity.ok(achievements);
    }

    @GetMapping("/experience")
    @ApiOperation(value = "Get achievements by maximum experience points", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of achievements")
    public ResponseEntity<List<Achievement>> getAchievementsByExperiencePoints(@ApiParam(value = "Maximum experience points", required = true) @RequestParam int maxPoints) {
        List<Achievement> achievements = achievementService.getAchievementsByExperiencePoints(maxPoints);
        return ResponseEntity.ok(achievements);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an achievement", response = Achievement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Achievement successfully updated"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "Achievement not found")
    })
    public ResponseEntity<Achievement> updateAchievement(
            @ApiParam(value = "ID of the achievement to be updated", required = true) @PathVariable String id,
            @ApiParam(value = "Updated achievement object", required = true) @RequestBody Achievement achievement) {
        if (!id.equals(achievement.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Achievement updatedAchievement = achievementService.updateAchievement(achievement);
        return ResponseEntity.ok(updatedAchievement);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an achievement")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Achievement successfully deleted"),
            @ApiResponse(code = 404, message = "Achievement not found")
    })
    public ResponseEntity<Void> deleteAchievement(@ApiParam(value = "ID of the achievement to be deleted", required = true) @PathVariable String id) {
        achievementService.deleteAchievement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}/unlocked")
    @ApiOperation(value = "Get unlocked achievements for a user", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of unlocked achievements")
    public ResponseEntity<List<Achievement>> getUnlockedAchievements(@ApiParam(value = "ID of the user", required = true) @PathVariable String userId) {
        List<Achievement> unlockedAchievements = achievementService.getUnlockedAchievements(userId);
        return ResponseEntity.ok(unlockedAchievements);
    }

    @PostMapping("/{achievementId}/unlock/{userId}")
    @ApiOperation(value = "Unlock an achievement for a user")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Achievement successfully unlocked"),
            @ApiResponse(code = 404, message = "Achievement or user not found")
    })
    public ResponseEntity<Void> unlockAchievement(
            @ApiParam(value = "ID of the achievement to be unlocked", required = true) @PathVariable String achievementId,
            @ApiParam(value = "ID of the user", required = true) @PathVariable String userId) {
        achievementService.unlockAchievement(userId, achievementId);
        return ResponseEntity.noContent().build();
    }
}