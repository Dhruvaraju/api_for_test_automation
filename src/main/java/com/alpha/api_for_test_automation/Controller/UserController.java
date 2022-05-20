package com.alpha.api_for_test_automation.Controller;

import com.alpha.api_for_test_automation.Entity.User;
import com.alpha.api_for_test_automation.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userService.fetchAllUsers();
    }

    @RequestMapping(value = "/api/register", method = RequestMethod.POST)
    public User registerUser(@RequestBody User user){
        return userService.addOrUpdateUser(user);
    }

    @RequestMapping(value = "/api/update", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user){
        return userService.addOrUpdateUser(user);
    }

    @RequestMapping(value = "/api/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeUser(@RequestBody User user){
        userService.removeUser(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("User Removed");
    }
}
