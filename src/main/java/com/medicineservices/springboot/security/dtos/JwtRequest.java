package com.medicineservices.springboot.security.dtos;

import lombok.Data;

@Data
public class JwtRequest {
    private String iin;
    private String password;
}
