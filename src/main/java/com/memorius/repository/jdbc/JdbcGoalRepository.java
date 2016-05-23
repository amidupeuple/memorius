package com.memorius.repository.jdbc;

import com.memorius.model.Goal;
import com.memorius.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dpivovar on 23.05.2016.
 */
@Repository
public class JdbcGoalRepository implements GoalRepository {
    private static final String GET_GOAL_BY_ID = "select * from goals where id = ?";

    //JdbcOperations - interface implemented by JdbcTemplate
    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcGoalRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public Goal getGoal(int id) {
        return jdbcOperations.queryForObject(GET_GOAL_BY_ID, new GoalRowMapper(), id);
    }


    private static final class  GoalRowMapper implements RowMapper<Goal> {

        public Goal mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Goal(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description")
            );
        }
    }
}
