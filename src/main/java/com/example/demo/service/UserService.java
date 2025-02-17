package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User CreateUser(User user);

    ResponseEntity Login(User user);
}
