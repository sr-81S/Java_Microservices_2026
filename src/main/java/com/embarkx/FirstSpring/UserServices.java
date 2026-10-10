package com.embarkx.FirstSpring;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                .filter(user-> Objects.equals(user.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public User updateUserById(Long id, User user) {
        users.stream()
                .filter(u -> Objects.equals(u.getId(), id))
                .findFirst()
                .ifPresent(existingUser -> {
                    existingUser.setFirstName(user.getFirstName());
                    existingUser.setLastName(user.getLastName());
                });
        return getUserById(id);
    }


}
