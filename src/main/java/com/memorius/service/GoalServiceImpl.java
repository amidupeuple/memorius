package com.memorius.service;

import com.memorius.model.Goal;
import com.memorius.repository.SpringDataJpaGoalRepository;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<String> updateGoal(Goal goal) {
        Goal prevGoal = goalRepository.findOne(goal.getId());
        List<String> updatedProperties = new ArrayList<>();
        if (!DateUtils.isSameDay(goal.getDeadline(), prevGoal.getDeadline())) {
            prevGoal.setDeadline(goal.getDeadline());
            updatedProperties.add("Deadline");
        }

        if (!goal.getDescription().equals(prevGoal.getDescription())) {
            prevGoal.setDescription(goal.getDescription());
            updatedProperties.add("Description");
        }

        if (!goal.getNotificationFrequency().equals(prevGoal.getNotificationFrequency())) {
            prevGoal.setNotificationFrequency(goal.getNotificationFrequency());
            updatedProperties.add("Notification Frequency");
        }

        if (!goal.getStatus().equals(prevGoal.getStatus())) {
            prevGoal.setStatus(goal.getStatus());
            updatedProperties.add("Status");
        }

        if (((goal.getParticipants() == null) && (prevGoal.getParticipants() != null)) ||
                ((goal.getParticipants() != null) && (prevGoal.getParticipants() == null)) ||
                ((goal.getParticipants() != null) && (prevGoal.getParticipants() != null) && (!goal.getParticipants().equals(prevGoal.getParticipants())))) {
            prevGoal.setParticipants(goal.getParticipants());
            updatedProperties.add("Participants");
        }

        if (updatedProperties.size() != 0) {
            goalRepository.save(prevGoal);
        }

        return updatedProperties;
    }

    @Override
    public List<Goal> findGoalsByStatusAndNotificationFrequencyAllIgnoresCase(String status, String notificationFrequency) {
        return goalRepository.readGoalsByStatusAndNotificationFrequency(status, notificationFrequency);
    }
}
