package com.memorius.repository.jpa;

import com.memorius.model.Goal;
import com.memorius.repository.GoalRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by dpivovar on 25.05.2016.
 */
@Repository
@Transactional
public class JpaGoalRepository implements GoalRepository {

    /*@PersistenceUnit
    private EntityManagerFactory emf;*/
    @PersistenceContext
    private EntityManager em;

    @Override
    public Goal getGoal(int id) {
        return em.find(Goal.class, id);
    }

    @Override
    public void addGoal(Goal goal) {
        em.persist(goal);
    }

    @Override
    public List<Goal> getAllGoals() {
        return em.createQuery("from Goal g").getResultList();
    }
}
