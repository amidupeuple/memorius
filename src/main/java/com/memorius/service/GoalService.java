package com.memorius.service;

import com.memorius.model.Goal;

import java.util.List;

/**
 * Created by dpivovar on 23.05.2016.
 */
public interface GoalService {
    Goal findGoalById(int id);
    void saveGoal(Goal goal);
    List<Goal> findAllGoals();
}
