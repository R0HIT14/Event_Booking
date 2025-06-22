package com.EventBookingSystem.Service;

import com.EventBookingSystem.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User updateUser(Long Id, User user);
    List<User> getAllUsers();
    User getUserById(Long Id);
    void deleteUser(Long Id);
    Page<User> getAllUsersPaginated(Pageable pageable);
}
