package com.spring.calculator.service;

import com.spring.calculator.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
