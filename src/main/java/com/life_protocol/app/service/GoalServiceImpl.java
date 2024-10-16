package com.life_protocol.app.service;

import com.life_protocol.app.model.Goal;
import com.life_protocol.app.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    @Autowired
    public GoalServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public Goal createGoal(Goal goal) {
        // Add any validation or business logic here
        return goalRepository.save(goal);
    }

    @Override
    public Optional<Goal> getGoalById(String id) {
        return goalRepository.findById(id);
    }

    @Override
    public List<Goal> getGoalsByUserId(String userId) {
        return goalRepository.findByUserId(userId);
    }

    @Override
    public List<Goal> getActiveGoalsByUserId(String userId) {
        return goalRepository.findByUserIdAndCompleted(userId, false);
    }

    @Override
    public List<Goal> getGoalsByUserIdAndDateRange(String userId, LocalDate startDate, LocalDate endDate) {
        return goalRepository.findByUserIdAndStartDateBeforeAndEndDateAfter(userId, startDate, endDate);
    }

    @Override
    public Goal updateGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    @Override
    public void deleteGoal(String id) {
        goalRepository.deleteById(id);
    }

    @Override
    public List<Goal> getCompletedGoalsByUserId(String userId) {
        return goalRepository.findByUserIdAndCompleted(userId, true);
    }
}