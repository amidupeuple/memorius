package com.memorius.repository;

import com.memorius.model.Goal;

import java.util.List;

/**
 * Created by dpivovar on 23.05.2016.
 */
public interface GoalRepository {
    Goal getGoal(int id);
    void addGoal(Goal goal);
    List<Goal> getAllGoals();
}
