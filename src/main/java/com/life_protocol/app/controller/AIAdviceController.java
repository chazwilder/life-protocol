package com.life_protocol.app.controller;

import com.life_protocol.app.model.AIAdvice;
import com.life_protocol.app.service.AIAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai-advice")
public class AIAdviceController {

    private final AIAdviceService aiAdviceService;

    @Autowired
    public AIAdviceController(AIAdviceService aiAdviceService) {
        this.aiAdviceService = aiAdviceService;
    }

    @PostMapping
    public ResponseEntity<AIAdvice> createAIAdvice(@RequestBody AIAdvice aiAdvice) {
        AIAdvice createdAdvice = aiAdviceService.createAIAdvice(aiAdvice);
        return new ResponseEntity<>(createdAdvice, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AIAdvice> getAIAdviceById(@PathVariable String id) {
        return aiAdviceService.getAIAdviceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AIAdvice>> getAIAdviceByUserId(@PathVariable String userId) {
        List<AIAdvice> advice = aiAdviceService.getAIAdviceByUserId(userId);
        return ResponseEntity.ok(advice);
    }

    @GetMapping("/user/{userId}/category/{category}")
    public ResponseEntity<List<AIAdvice>> getAIAdviceByUserIdAndCategory(
            @PathVariable String userId, @PathVariable String category) {
        List<AIAdvice> advice = aiAdviceService.getAIAdviceByUserIdAndCategory(userId, category);
        return ResponseEntity.ok(advice);
    }

    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<AIAdvice>> getUnreadAIAdviceByUserId(@PathVariable String userId) {
        List<AIAdvice> advice = aiAdviceService.getUnreadAIAdviceByUserId(userId);
        return ResponseEntity.ok(advice);
    }

    @GetMapping("/user/{userId}/recent")
    public ResponseEntity<List<AIAdvice>> getRecentAIAdviceByUserId(
            @PathVariable String userId, @RequestParam(defaultValue = "10") int limit) {
        List<AIAdvice> advice = aiAdviceService.getRecentAIAdviceByUserId(userId, limit);
        return ResponseEntity.ok(advice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AIAdvice> updateAIAdvice(@PathVariable String id, @RequestBody AIAdvice aiAdvice) {
        if (!id.equals(aiAdvice.getId())) {
            return ResponseEntity.badRequest().build();
        }
        AIAdvice updatedAdvice = aiAdviceService.updateAIAdvice(aiAdvice);
        return ResponseEntity.ok(updatedAdvice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAIAdvice(@PathVariable String id) {
        aiAdviceService.deleteAIAdvice(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/mark-read")
    public ResponseEntity<Void> markAIAdviceAsRead(@PathVariable String id) {
        aiAdviceService.markAIAdviceAsRead(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/mark-implemented")
    public ResponseEntity<Void> markAIAdviceAsImplemented(@PathVariable String id) {
        aiAdviceService.markAIAdviceAsImplemented(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/rate")
    public ResponseEntity<Void> rateAIAdvice(@PathVariable String id, @RequestParam int rating) {
        aiAdviceService.rateAIAdvice(id, rating);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/feedback")
    public ResponseEntity<Void> provideFeedbackForAIAdvice(@PathVariable String id, @RequestBody String feedback) {
        aiAdviceService.provideFeedbackForAIAdvice(id, feedback);
        return ResponseEntity.noContent().build();
    }
}