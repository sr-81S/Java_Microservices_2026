package com.embarkx.FirstSpring;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserServices userServices;


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

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        // Logic to retrieve a user by ID from the database
        var res = userServices.getUserById(id);
        if (res == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            Map.of("message", "User with id " + id + " not found")
                    );
        }
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
