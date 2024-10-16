package com.life_protocol.app.service;

import com.life_protocol.app.model.Goal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GoalService {
    Goal createGoal(Goal goal);
    Optional<Goal> getGoalById(String id);
    List<Goal> getGoalsByUserId(String userId);
    List<Goal> getActiveGoalsByUserId(String userId);
    List<Goal> getGoalsByUserIdAndDateRange(String userId, LocalDate startDate, LocalDate endDate);
    Goal updateGoal(Goal goal);
    void deleteGoal(String id);
    List<Goal> getCompletedGoalsByUserId(String userId);
}