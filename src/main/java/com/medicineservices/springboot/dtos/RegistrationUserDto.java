package com.medicineservices.springboot.dtos;

import lombok.Data;

@Data
public class RegistrationUserDto {
    private String iin;
    private String password;
    private String confirmPassword;
    private String email;
}
