package com.EventBookingSystem.Service.Implementation;

import com.EventBookingSystem.Entity.User;
import com.EventBookingSystem.Repository.UserRepository;
import com.EventBookingSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long Id, User user) {
        User existing = getUserById(Id);
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword());
        existing.setRole(user.getRole());
        return userRepository.save(existing);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long Id) {
        return userRepository.findById(Id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteUser(Long Id) {
        userRepository.deleteById(Id);
    }
}
