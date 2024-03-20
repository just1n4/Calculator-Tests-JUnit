package com.spring.calculator.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
