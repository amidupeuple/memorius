package com.memorius.repository;

import com.memorius.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dpivovar on 23.06.2016.
 */
public interface SpringDataJpaUserRepository extends JpaRepository<User, Integer>{
    User readByUserName(String userName);
}
