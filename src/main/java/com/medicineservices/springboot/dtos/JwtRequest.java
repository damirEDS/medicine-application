package com.medicineservices.springboot.dtos;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
