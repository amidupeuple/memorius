package com.memorius.repository.jdbc;

import com.memorius.model.Goal;
import com.memorius.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dpivovar on 23.05.2016.
 */
@Repository
public class JdbcGoalRepository implements GoalRepository {
    private static final String GET_GOAL_BY_ID = "select * from goals where id = ?";
    private static final String INSERT_GOAL = "insert into goals values (?, ?, ?, ?)";
    private static final String GET_ALL_GOALS = "select * from goals";

    //JdbcOperations - interface implemented by JdbcTemplate
    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcGoalRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public Goal getGoal(int id) {
        return jdbcOperations.queryForObject(
                GET_GOAL_BY_ID,
                (rs, rowNum) -> {
                    return new Goal(
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDate("deadline"),
                            null,
                            null,
                            null
                    );
                },
                id);
    }

    @Override
    public void addGoal(Goal goal) {
        jdbcOperations.update(INSERT_GOAL,
                goal.getId(),
                goal.getName(),
                goal.getDescription(),
                goal.getDeadline());
    }

    @Override
    public List<Goal> getAllGoals() {
        List<Goal> result = new ArrayList<>();
        result = jdbcOperations.query(GET_ALL_GOALS,
                (rs, rowNum) -> {
                    return new Goal(
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDate("deadline"),
                            null,
                            null,
                            null
                    );
                });
        return result;
    }
}
