package com.embarkx.FirstSpring.services;

import com.embarkx.FirstSpring.dtos.AddressDto;
import com.embarkx.FirstSpring.dtos.UserRequest;
import com.embarkx.FirstSpring.dtos.UserResponce;
import com.embarkx.FirstSpring.models.Address;
import com.embarkx.FirstSpring.models.User;
import com.embarkx.FirstSpring.models.UserRole;
import com.embarkx.FirstSpring.repository.UserRepositries;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepositries userRepositries;

    public List<UserResponce> getAllUsers() {
        var users = userRepositries.findAll();
        return users.stream()
                .map(this::mapToUserResponse)
                .toList();
    }

    public UserResponce addUser(UserRequest user) {

        var newUser = mapToUser(user);
        newUser.setRole(UserRole.ADMIN);
        userRepositries.save(newUser);
        return mapToUserResponse(newUser);
    }

    public UserResponce getUserById(Long id) {
        var  user = userRepositries.findById(id).orElse(null);
        if(user == null) {
            return null;
        }
        return mapToUserResponse(user);
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


    //function map to user response
    private UserResponce mapToUserResponse(User user) {
        UserResponce userResponce = new UserResponce();
        userResponce.setId(user.getId());
        userResponce.setFirstName(user.getFirstName());
        userResponce.setLastName(user.getLastName());
        userResponce.setEmail(user.getEmail());
        userResponce.setPhoneNumber(user.getPhoneNumber());
        userResponce.setRole(user.getRole());
        if (user.getAddress() != null) {
            var addressDto = new AddressDto();
            addressDto.setStreet(user.getAddress().getStreet());
            addressDto.setCity(user.getAddress().getCity());
            addressDto.setState(user.getAddress().getState());
            addressDto.setCountry(user.getAddress().getCountry());
            addressDto.setZipcode(user.getAddress().getZipcode());
            userResponce.setAddress(addressDto);
        }
        return userResponce;
    }

    //map user request to user
    private User mapToUser(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        if(userRequest.getAddress() != null) {
            var address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZipcode(userRequest.getAddress().getZipcode());
            user.setAddress(address);
        }
        return user;
    }


}
