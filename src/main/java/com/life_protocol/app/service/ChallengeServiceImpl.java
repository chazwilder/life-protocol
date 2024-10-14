package com.life_protocol.app.service;

import com.life_protocol.app.model.Challenge;
import com.life_protocol.app.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository challengeRepository;

    @Autowired
    public ChallengeServiceImpl(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    @Override
    public Challenge createChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    @Override
    public Optional<Challenge> getChallengeById(String id) {
        return challengeRepository.findById(id);
    }

    @Override
    public List<Challenge> getChallengesByCreator(String creatorId) {
        return challengeRepository.findByCreatorId(creatorId);
    }

    @Override
    public List<Challenge> getChallengesByParticipant(String userId) {
        return challengeRepository.findByParticipantsContaining(userId);
    }

    @Override
    public List<Challenge> getActiveAndUpcomingChallenges() {
        LocalDateTime now = LocalDateTime.now();
        return challengeRepository.findByStartDateBeforeAndEndDateAfter(now, now);
    }

    @Override
    public List<Challenge> getChallengesByStatus(Challenge.ChallengeStatus status) {
        return challengeRepository.findByStatus(status);
    }

    @Override
    public List<Challenge> getChallengesByType(Challenge.ChallengeType type) {
        return challengeRepository.findByType(type);
    }

    @Override
    public Challenge updateChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    @Override
    public void deleteChallenge(String id) {
        challengeRepository.deleteById(id);
    }

    @Override
    public void addParticipant(String challengeId, String userId) {
        challengeRepository.findById(challengeId).ifPresent(challenge -> {
            if (!challenge.getParticipantIds().contains(userId)) {
                challenge.getParticipantIds().add(userId);
                challengeRepository.save(challenge);
            }
        });
    }

    @Override
    public void removeParticipant(String challengeId, String userId) {
        challengeRepository.findById(challengeId).ifPresent(challenge -> {
            challenge.getParticipantIds().remove(userId);
            challengeRepository.save(challenge);
        });
    }
}