package com.embarkx.FirstSpring;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {
    private List<User> users = new ArrayList<User>();
    private Long initialId = 1L;

    public List<User> getAllUsers() {
        return users;
    }

    public User addUser(User user) {
        user.setId(initialId++);
        users.add(user);
        return user;
    }

    public User getUserById(Long id) {
        return users.stream()
                .filter(user-> user.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
