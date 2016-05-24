package com.memorius.service;

import com.memorius.model.Goal;
import com.memorius.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dpivovar on 23.05.2016.
 */
@Service
public class GoalServiceImpl implements GoalService {


    private GoalRepository goalRepository;

    @Autowired
    @Qualifier("hibernateGoalRepository")
    public void setGoalRepository(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public GoalServiceImpl() {
    }

    public GoalServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public Goal findGoalById(int id) {
        return goalRepository.getGoal(id);
    }

    @Override
    public void saveGoal(Goal goal) {
        goalRepository.addGoal(goal);
    }

    @Override
    public List<Goal> findAllGoals() {
        return goalRepository.getAllGoals();
    }
}
