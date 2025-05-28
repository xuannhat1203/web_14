package com.data.service;

import com.data.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    boolean addUser(User user);
}
