package com.alpha.api_for_test_automation.Service;

import com.alpha.api_for_test_automation.Entity.User;
import com.alpha.api_for_test_automation.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public User addOrUpdateUser(User user) {
        return userRepo.save(user);
    }

    public List<User> fetchAllUsers() {
        return userRepo.findAll();
    }

    public void removeUser(User user) {
        userRepo.delete(user);
    }
}
