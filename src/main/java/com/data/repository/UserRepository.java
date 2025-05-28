package com.data.repository;

import com.data.model.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUser();
    boolean addUser(User user);
}
