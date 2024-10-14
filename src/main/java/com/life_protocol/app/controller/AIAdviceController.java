package com.life_protocol.app.controller;

import com.life_protocol.app.model.AIAdvice;
import com.life_protocol.app.service.AIAdviceService;
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
@RequestMapping("/api/ai-advice")
@Tag(name = "AI Advice Management", description = "Operations pertaining to AI-generated advice in the Life Protocol application")
public class AIAdviceController {

    private final AIAdviceService aiAdviceService;

    @Autowired
    public AIAdviceController(AIAdviceService aiAdviceService) {
        this.aiAdviceService = aiAdviceService;
    }

    @PostMapping
    @Operation(summary = "Create a new AI advice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "AI advice successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<AIAdvice> createAIAdvice(@Parameter(description = "AI advice object to be created", required = true) @RequestBody AIAdvice aiAdvice) {
        AIAdvice createdAdvice = aiAdviceService.createAIAdvice(aiAdvice);
        return new ResponseEntity<>(createdAdvice, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an AI advice by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved AI advice"),
            @ApiResponse(responseCode = "404", description = "AI advice not found")
    })
    public ResponseEntity<AIAdvice> getAIAdviceById(@Parameter(description = "ID of the AI advice to be retrieved", required = true) @PathVariable String id) {
        return aiAdviceService.getAIAdviceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get AI advice for a user")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of AI advice")
    public ResponseEntity<List<AIAdvice>> getAIAdviceByUserId(@Parameter(description = "ID of the user", required = true) @PathVariable String userId) {
        List<AIAdvice> advice = aiAdviceService.getAIAdviceByUserId(userId);
        return ResponseEntity.ok(advice);
    }

    @GetMapping("/user/{userId}/category/{category}")
    @Operation(summary = "Get AI advice for a user by category")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of AI advice")
    public ResponseEntity<List<AIAdvice>> getAIAdviceByUserIdAndCategory(
            @Parameter(description = "ID of the user", required = true) @PathVariable String userId,
            @Parameter(description = "Category of advice", required = true) @PathVariable String category) {
        List<AIAdvice> advice = aiAdviceService.getAIAdviceByUserIdAndCategory(userId, category);
        return ResponseEntity.ok(advice);
    }

    @GetMapping("/user/{userId}/unread")
    @Operation(summary = "Get unread AI advice for a user")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of unread AI advice")
    public ResponseEntity<List<AIAdvice>> getUnreadAIAdviceByUserId(@Parameter(description = "ID of the user", required = true) @PathVariable String userId) {
        List<AIAdvice> advice = aiAdviceService.getUnreadAIAdviceByUserId(userId);
        return ResponseEntity.ok(advice);
    }

    @GetMapping("/user/{userId}/recent")
    @Operation(summary = "Get recent AI advice for a user")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of recent AI advice")
    public ResponseEntity<List<AIAdvice>> getRecentAIAdviceByUserId(
            @Parameter(description = "ID of the user", required = true) @PathVariable String userId,
            @Parameter(description = "Limit of advice to retrieve", required = false) @RequestParam(defaultValue = "10") int limit) {
        List<AIAdvice> advice = aiAdviceService.getRecentAIAdviceByUserId(userId, limit);
        return ResponseEntity.ok(advice);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an AI advice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "AI advice successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "AI advice not found")
    })
    public ResponseEntity<AIAdvice> updateAIAdvice(
            @Parameter(description = "ID of the AI advice to be updated", required = true) @PathVariable String id,
            @Parameter(description = "Updated AI advice object", required = true) @RequestBody AIAdvice aiAdvice) {
        if (!id.equals(aiAdvice.getId())) {
            return ResponseEntity.badRequest().build();
        }
        AIAdvice updatedAdvice = aiAdviceService.updateAIAdvice(aiAdvice);
        return ResponseEntity.ok(updatedAdvice);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an AI advice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "AI advice successfully deleted"),
            @ApiResponse(responseCode = "404", description = "AI advice not found")
    })
    public ResponseEntity<Void> deleteAIAdvice(@Parameter(description = "ID of the AI advice to be deleted", required = true) @PathVariable String id) {
        aiAdviceService.deleteAIAdvice(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/mark-read")
    @Operation(summary = "Mark an AI advice as read")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "AI advice successfully marked as read"),
            @ApiResponse(responseCode = "404", description = "AI advice not found")
    })
    public ResponseEntity<Void> markAIAdviceAsRead(@Parameter(description = "ID of the AI advice to be marked as read", required = true) @PathVariable String id) {
        aiAdviceService.markAIAdviceAsRead(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/mark-implemented")
    @Operation(summary = "Mark an AI advice as implemented")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "AI advice successfully marked as implemented"),
            @ApiResponse(responseCode = "404", description = "AI advice not found")
    })
    public ResponseEntity<Void> markAIAdviceAsImplemented(@Parameter(description = "ID of the AI advice to be marked as implemented", required = true) @PathVariable String id) {
        aiAdviceService.markAIAdviceAsImplemented(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/rate")
    @Operation(summary = "Rate an AI advice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "AI advice successfully rated"),
            @ApiResponse(responseCode = "404", description = "AI advice not found")
    })
    public ResponseEntity<Void> rateAIAdvice(
            @Parameter(description = "ID of the AI advice to be rated", required = true) @PathVariable String id,
            @Parameter(description = "Rating value", required = true) @RequestParam int rating) {
        aiAdviceService.rateAIAdvice(id, rating);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/feedback")
    @Operation(summary = "Provide feedback for an AI advice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Feedback successfully provided for AI advice"),
            @ApiResponse(responseCode = "404", description = "AI advice not found")
    })
    public ResponseEntity<Void> provideFeedbackForAIAdvice(
            @Parameter(description = "ID of the AI advice to provide feedback for", required = true) @PathVariable String id,
            @Parameter(description = "Feedback text", required = true) @RequestBody String feedback) {
        aiAdviceService.provideFeedbackForAIAdvice(id, feedback);
        return ResponseEntity.noContent().build();
    }
}