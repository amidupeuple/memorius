package com.memorius.service;

import com.memorius.model.Goal;
import com.memorius.repository.GoalRepository;
import com.memorius.repository.SpringDataJpaGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dpivovar on 23.05.2016.
 */
@Service
public class GoalServiceImpl implements GoalService {

    private SpringDataJpaGoalRepository goalRepository;

    @Autowired
    public void setGoalRepository(SpringDataJpaGoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    /*private GoalRepository goalRepository;

    @Autowired
    @Qualifier("jpaGoalRepository")
    public void setGoalRepository(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public GoalServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }
    */

    public GoalServiceImpl() {
    }

    public Goal findGoalById(int id) {
        //return goalRepository.getGoal(id);
        return goalRepository.findOne(id);
    }

    @Override
    public void saveGoal(Goal goal) {
        //goalRepository.addGoal(goal);
        goalRepository.save(goal);
    }

    @Override
    public List<Goal> findAllGoals() {
        //return goalRepository.getAllGoals();
        return goalRepository.findAll();
    }
}
