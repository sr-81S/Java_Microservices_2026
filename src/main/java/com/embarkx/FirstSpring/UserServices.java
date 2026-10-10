package com.embarkx.FirstSpring;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {
    private List<User> users = new ArrayList<User>();

    public List<User> getAllUsers() {
        return users;
    }

    public User addUser(User user) {
        users.add(user);
        return user;
    }
}
