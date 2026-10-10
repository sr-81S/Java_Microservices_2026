package com.embarkx.FirstSpring;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServices userServices;
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        // Logic to retrieve all users from the database
        return userServices.getAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        // Logic to create a new user in the database
       var res =  userServices.addUser(user);
       return res;
    }
}
