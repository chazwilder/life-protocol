package com.life_protocol.app.controller;

import com.life_protocol.app.model.ProtocolReview;
import com.life_protocol.app.service.ProtocolReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/protocol-reviews")
public class ProtocolReviewController {

    private final ProtocolReviewService protocolReviewService;

    @Autowired
    public ProtocolReviewController(ProtocolReviewService protocolReviewService) {
        this.protocolReviewService = protocolReviewService;
    }

    @PostMapping
    public ResponseEntity<ProtocolReview> createReview(@RequestBody ProtocolReview review) {
        ProtocolReview createdReview = protocolReviewService.createReview(review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProtocolReview> getReviewById(@PathVariable String id) {
        return protocolReviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/protocol/{protocolId}")
    public ResponseEntity<List<ProtocolReview>> getReviewsByProtocolId(@PathVariable String protocolId) {
        List<ProtocolReview> reviews = protocolReviewService.getReviewsByProtocolId(protocolId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProtocolReview>> getReviewsByUserId(@PathVariable String userId) {
        List<ProtocolReview> reviews = protocolReviewService.getReviewsByUserId(userId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<ProtocolReview>> getReviewsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<ProtocolReview> reviews = protocolReviewService.getReviewsByDateRange(start, end);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<ProtocolReview>> getReviewsByRatingGreaterThan(@PathVariable int rating) {
        List<ProtocolReview> reviews = protocolReviewService.getReviewsByRatingGreaterThan(rating);
        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProtocolReview> updateReview(@PathVariable String id, @RequestBody ProtocolReview review) {
        review.setId(id);
        ProtocolReview updatedReview = protocolReviewService.updateReview(review);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable String id) {
        protocolReviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}