package com.memorius.repository;

import com.memorius.model.Goal;

/**
 * Created by dpivovar on 23.05.2016.
 */
public interface GoalRepository {
    Goal getGoal(int id);
}
