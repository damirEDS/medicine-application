package com.medicineservices.springboot.dtos;

import lombok.Data;

@Data
public class JwtRequest {
    private String iin;
    private String password;
}
