package com.life_protocol.app.service;

import com.life_protocol.app.model.Achievement;
import java.util.List;
import java.util.Optional;

public interface AchievementService {
    Achievement createAchievement(Achievement achievement);
    Optional<Achievement> getAchievementById(String id);
    List<Achievement> getAllAchievements();
    List<Achievement> getAchievementsByCategory(String category);
    List<Achievement> getAchievementsByExperiencePoints(int maxPoints);
    Achievement updateAchievement(Achievement achievement);
    void deleteAchievement(String id);
    List<Achievement> getUnlockedAchievements(String userId);
    void unlockAchievement(String userId, String achievementId);
}