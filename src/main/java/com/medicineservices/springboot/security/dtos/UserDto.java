package com.medicineservices.springboot.security.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.medicineservices.springboot.security.entities.Role;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String iin;
    private String email;
}
