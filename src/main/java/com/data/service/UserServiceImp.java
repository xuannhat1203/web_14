package com.data.service;

import com.data.model.User;
import com.data.repository.UserRepository;
import com.data.repository.UserRepositoryImp;

import java.util.List;
public class UserServiceImp implements UserService {
    public UserRepository userRepository;

    public UserServiceImp() {
        userRepository = new UserRepositoryImp();
    }
    @Override
    public List<User> getAllUser() {
        return userRepository.getAllUser();
    }

    @Override
    public boolean addUser(User user) {
        return userRepository.addUser(user);
    }
}
