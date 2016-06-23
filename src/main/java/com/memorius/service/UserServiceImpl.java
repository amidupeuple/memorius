package com.memorius.service;

import com.memorius.model.User;
import com.memorius.repository.SpringDataJpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dpivovar on 23.06.2016.
 */
@Service
public class UserServiceImpl implements UserService {

    private SpringDataJpaUserRepository userRepository;

    @Autowired
    public void setUserRepository(SpringDataJpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.readByUserName(userName);
    }
}
