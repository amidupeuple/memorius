package com.memorius.repository.hibernate;

import com.memorius.model.Goal;
import com.memorius.repository.GoalRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dpivovar on 24.05.2016.
 */
//@Repository
@Transactional
public class HibernateGoalRepository implements GoalRepository {


    private SessionFactory sessionFactory;

    @Autowired
    public HibernateGoalRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Goal getGoal(int id) {
        return (Goal) currentSession().get(Goal.class, id);
    }

    @Override
    public void addGoal(Goal goal) {
        currentSession().save(goal);
    }

    @Override
    public List<Goal> getAllGoals() {
        return (List<Goal>) currentSession().createCriteria(Goal.class).list();
    }
}
