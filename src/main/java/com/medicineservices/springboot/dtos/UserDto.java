package com.medicineservices.springboot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.medicineservices.springboot.entities.Role;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String iin;
    private String email;
}
