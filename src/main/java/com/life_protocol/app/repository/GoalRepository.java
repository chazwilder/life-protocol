package com.life_protocol.app.repository;

import com.life_protocol.app.model.Goal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GoalRepository extends MongoRepository<Goal, String> {
    List<Goal> findByCompletedFalseAndEndDateAfter(LocalDate date);
}