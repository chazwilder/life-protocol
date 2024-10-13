package com.life_protocol.app.controller;

import com.life_protocol.app.model.AIAdvice;
import com.life_protocol.app.service.AIAdviceService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai-advice")
@Api(tags = "AI Advice Management", description = "Operations pertaining to AI-generated advice in the Life Protocol application")
public class AIAdviceController {

    private final AIAdviceService aiAdviceService;

    @Autowired
    public AIAdviceController(AIAdviceService aiAdviceService) {
        this.aiAdviceService = aiAdviceService;
    }

    @PostMapping
    @ApiOperation(value = "Create a new AI advice", response = AIAdvice.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "AI advice successfully created"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<AIAdvice> createAIAdvice(@ApiParam(value = "AI advice object to be created", required = true) @RequestBody AIAdvice aiAdvice) {
        AIAdvice createdAdvice = aiAdviceService.createAIAdvice(aiAdvice);
        return new ResponseEntity<>(createdAdvice, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get an AI advice by ID", response = AIAdvice.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved AI advice"),
            @ApiResponse(code = 404, message = "AI advice not found")
    })
    public ResponseEntity<AIAdvice> getAIAdviceById(@ApiParam(value = "ID of the AI advice to be retrieved", required = true) @PathVariable String id) {
        return aiAdviceService.getAIAdviceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @ApiOperation(value = "Get AI advice for a user", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of AI advice")
    public ResponseEntity<List<AIAdvice>> getAIAdviceByUserId(@ApiParam(value = "ID of the user", required = true) @PathVariable String userId) {
        List<AIAdvice> advice = aiAdviceService.getAIAdviceByUserId(userId);
        return ResponseEntity.ok(advice);
    }

    @GetMapping("/user/{userId}/category/{category}")
    @ApiOperation(value = "Get AI advice for a user by category", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of AI advice")
    public ResponseEntity<List<AIAdvice>> getAIAdviceByUserIdAndCategory(
            @ApiParam(value = "ID of the user", required = true) @PathVariable String userId,
            @ApiParam(value = "Category of advice", required = true) @PathVariable String category) {
        List<AIAdvice> advice = aiAdviceService.getAIAdviceByUserIdAndCategory(userId, category);
        return ResponseEntity.ok(advice);
    }

    @GetMapping("/user/{userId}/unread")
    @ApiOperation(value = "Get unread AI advice for a user", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of unread AI advice")
    public ResponseEntity<List<AIAdvice>> getUnreadAIAdviceByUserId(@ApiParam(value = "ID of the user", required = true) @PathVariable String userId) {
        List<AIAdvice> advice = aiAdviceService.getUnreadAIAdviceByUserId(userId);
        return ResponseEntity.ok(advice);
    }

    @GetMapping("/user/{userId}/recent")
    @ApiOperation(value = "Get recent AI advice for a user", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of recent AI advice")
    public ResponseEntity<List<AIAdvice>> getRecentAIAdviceByUserId(
            @ApiParam(value = "ID of the user", required = true) @PathVariable String userId,
            @ApiParam(value = "Limit of advice to retrieve", defaultValue = "10") @RequestParam(defaultValue = "10") int limit) {
        List<AIAdvice> advice = aiAdviceService.getRecentAIAdviceByUserId(userId, limit);
        return ResponseEntity.ok(advice);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an AI advice", response = AIAdvice.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "AI advice successfully updated"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "AI advice not found")
    })
    public ResponseEntity<AIAdvice> updateAIAdvice(
            @ApiParam(value = "ID of the AI advice to be updated", required = true) @PathVariable String id,
            @ApiParam(value = "Updated AI advice object", required = true) @RequestBody AIAdvice aiAdvice) {
        if (!id.equals(aiAdvice.getId())) {
            return ResponseEntity.badRequest().build();
        }
        AIAdvice updatedAdvice = aiAdviceService.updateAIAdvice(aiAdvice);
        return ResponseEntity.ok(updatedAdvice);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an AI advice")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "AI advice successfully deleted"),
            @ApiResponse(code = 404, message = "AI advice not found")
    })
    public ResponseEntity<Void> deleteAIAdvice(@ApiParam(value = "ID of the AI advice to be deleted", required = true) @PathVariable String id) {
        aiAdviceService.deleteAIAdvice(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/mark-read")
    @ApiOperation(value = "Mark an AI advice as read")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "AI advice successfully marked as read"),
            @ApiResponse(code = 404, message = "AI advice not found")
    })
    public ResponseEntity<Void> markAIAdviceAsRead(@ApiParam(value = "ID of the AI advice to be marked as read", required = true) @PathVariable String id) {
        aiAdviceService.markAIAdviceAsRead(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/mark-implemented")
    @ApiOperation(value = "Mark an AI advice as implemented")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "AI advice successfully marked as implemented"),
            @ApiResponse(code = 404, message = "AI advice not found")
    })
    public ResponseEntity<Void> markAIAdviceAsImplemented(@ApiParam(value = "ID of the AI advice to be marked as implemented", required = true) @PathVariable String id) {
        aiAdviceService.markAIAdviceAsImplemented(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/rate")
    @ApiOperation(value = "Rate an AI advice")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "AI advice successfully rated"),
            @ApiResponse(code = 404, message = "AI advice not found")
    })
    public ResponseEntity<Void> rateAIAdvice(
            @ApiParam(value = "ID of the AI advice to be rated", required = true) @PathVariable String id,
            @ApiParam(value = "Rating value", required = true) @RequestParam int rating) {
        aiAdviceService.rateAIAdvice(id, rating);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/feedback")
    @ApiOperation(value = "Provide feedback for an AI advice")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Feedback successfully provided for AI advice"),
            @ApiResponse(code = 404, message = "AI advice not found")
    })
    public ResponseEntity<Void> provideFeedbackForAIAdvice(
            @ApiParam(value = "ID of the AI advice to provide feedback for", required = true) @PathVariable String id,
            @ApiParam(value = "Feedback text", required = true) @RequestBody String feedback) {
        aiAdviceService.provideFeedbackForAIAdvice(id, feedback);
        return ResponseEntity.noContent().build();
    }
}