package com.memorius.repository;

import com.memorius.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dpivovar on 26.05.2016.
 */
public interface SpringDataJpaGoalRepository extends JpaRepository<Goal, Integer> {
}
