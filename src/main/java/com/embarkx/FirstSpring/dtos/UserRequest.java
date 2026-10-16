package com.embarkx.FirstSpring.dtos;

import com.embarkx.FirstSpring.models.UserRole;
import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private AddressDto address;
}
