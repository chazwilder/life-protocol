package com.life_protocol.app.service;

import com.life_protocol.app.model.Goal;
import com.life_protocol.app.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    @Autowired
    public GoalServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public Goal createGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    @Override
    public Optional<Goal> getGoalById(Long id) {
        return goalRepository.findById(id);
    }

    @Override
    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    @Override
    public Goal updateGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    @Override
    public void deleteGoal(Long id) {
        goalRepository.deleteById(id);
    }

    @Override
    public List<Goal> getActiveGoals() {
        LocalDate now = LocalDate.now();
        return goalRepository.findAll().stream()
                .filter(goal -> !goal.isCompleted() &&
                        !goal.getEndDate().isBefore(now))
                .collect(Collectors.toList());
    }
}