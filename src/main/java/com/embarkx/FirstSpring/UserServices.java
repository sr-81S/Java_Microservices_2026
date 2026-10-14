package com.embarkx.FirstSpring;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepositries userRepositries;

    public List<User> getAllUsers() {
        return userRepositries.findAll();
    }

    public User addUser(User user) {

        userRepositries.save(user);
        return user;
    }

    public User getUserById(Long id) {
        return userRepositries.findById(id).orElse(null);
    }

    @Transactional
    public User updateUserById(Long id, User user) {
        var existingUser = userRepositries.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        userRepositries.save(existingUser);
        return existingUser;
    }


}
