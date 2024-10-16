package com.life_protocol.app.service;

import com.life_protocol.app.model.Achievement;
import com.life_protocol.app.repository.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;

    @Autowired
    public AchievementServiceImpl(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    @Override
    public Achievement createAchievement(Achievement achievement) {
        achievement.setCreatedAt(LocalDateTime.now());
        achievement.setUpdatedAt(LocalDateTime.now());
        return achievementRepository.save(achievement);
    }

    @Override
    public Optional<Achievement> getAchievementById(String id) {
        return achievementRepository.findById(id);
    }

    @Override
    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    @Override
    public List<Achievement> getAchievementsByCategory(String category) {
        return achievementRepository.findByCategory(category);
    }

    @Override
    public List<Achievement> getAchievementsByExperiencePoints(int maxPoints) {
        return achievementRepository.findByExperiencePointsLessThanEqual(maxPoints);
    }

    @Override
    public Achievement updateAchievement(Achievement achievement) {
        achievement.setUpdatedAt(LocalDateTime.now());
        return achievementRepository.save(achievement);
    }

    @Override
    public void deleteAchievement(String id) {
        achievementRepository.deleteById(id);
    }

    @Override
    public List<Achievement> getUnlockedAchievements(String userId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @Override
    public void unlockAchievement(String userId, String achievementId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }
}