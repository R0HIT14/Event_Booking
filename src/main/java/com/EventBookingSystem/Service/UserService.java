package com.EventBookingSystem.Service;

import com.EventBookingSystem.Entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User updateUser(Long Id, User user);
    List<User> getAllUsers();
    User getUserById(Long Id);
    void deleteUser(Long Id);
}
