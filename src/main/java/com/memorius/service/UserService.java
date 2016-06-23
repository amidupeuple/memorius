package com.memorius.service;

import com.memorius.model.User;

/**
 * Created by dpivovar on 23.06.2016.
 */
public interface UserService {
    User findUserByUserName(String userName);
}
