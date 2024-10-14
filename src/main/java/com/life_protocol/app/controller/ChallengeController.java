package com.life_protocol.app.controller;

import com.life_protocol.app.model.Challenge;
import com.life_protocol.app.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    private final ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @PostMapping
    public ResponseEntity<Challenge> createChallenge(@RequestBody Challenge challenge) {
        Challenge createdChallenge = challengeService.createChallenge(challenge);
        return new ResponseEntity<>(createdChallenge, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Challenge> getChallengeById(@PathVariable String id) {
        return challengeService.getChallengeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<List<Challenge>> getChallengesByCreator(@PathVariable String creatorId) {
        List<Challenge> challenges = challengeService.getChallengesByCreator(creatorId);
        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/participant/{userId}")
    public ResponseEntity<List<Challenge>> getChallengesByParticipant(@PathVariable String userId) {
        List<Challenge> challenges = challengeService.getChallengesByParticipant(userId);
        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Challenge>> getActiveAndUpcomingChallenges() {
        List<Challenge> challenges = challengeService.getActiveAndUpcomingChallenges();
        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Challenge>> getChallengesByStatus(@PathVariable Challenge.ChallengeStatus status) {
        List<Challenge> challenges = challengeService.getChallengesByStatus(status);
        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Challenge>> getChallengesByType(@PathVariable Challenge.ChallengeType type) {
        List<Challenge> challenges = challengeService.getChallengesByType(type);
        return ResponseEntity.ok(challenges);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Challenge> updateChallenge(@PathVariable String id, @RequestBody Challenge challenge) {
        challenge.setId(id);
        Challenge updatedChallenge = challengeService.updateChallenge(challenge);
        return ResponseEntity.ok(updatedChallenge);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChallenge(@PathVariable String id) {
        challengeService.deleteChallenge(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{challengeId}/participants/{userId}")
    public ResponseEntity<Void> addParticipant(@PathVariable String challengeId, @PathVariable String userId) {
        challengeService.addParticipant(challengeId, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{challengeId}/participants/{userId}")
    public ResponseEntity<Void> removeParticipant(@PathVariable String challengeId, @PathVariable String userId) {
        challengeService.removeParticipant(challengeId, userId);
        return ResponseEntity.ok().build();
    }
}