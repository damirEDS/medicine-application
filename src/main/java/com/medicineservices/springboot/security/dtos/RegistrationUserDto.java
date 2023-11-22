package com.medicineservices.springboot.security.dtos;

import lombok.Data;

@Data
public class RegistrationUserDto {
    private String iin;
    private String password;
    private String confirmPassword;
    private String email;
}
