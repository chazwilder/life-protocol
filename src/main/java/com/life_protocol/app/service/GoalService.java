package com.life_protocol.app.service;

import com.life_protocol.app.model.Goal;
import java.util.List;
import java.util.Optional;

public interface GoalService {
    Goal createGoal(Goal goal);
    Optional<Goal> getGoalById(Long id);
    List<Goal> getAllGoals();
    Goal updateGoal(Goal goal);
    void deleteGoal(Long id);
    List<Goal> getActiveGoals();
}