package com.embarkx.FirstSpring.dtos;

import com.embarkx.FirstSpring.models.UserRole;
import lombok.Data;

@Data
public class UserResponce {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private UserRole role;
    private AddressDto address;
}
