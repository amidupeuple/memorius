package com.memorius.repository;

import com.memorius.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by dpivovar on 26.05.2016.
 */
public interface SpringDataJpaGoalRepository extends JpaRepository<Goal, Integer> {
    List<Goal> readGoalsByStatusAndNotificationFrequency(String status, String notificationFrequency);
}
