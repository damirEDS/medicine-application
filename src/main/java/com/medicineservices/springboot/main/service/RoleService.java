package com.medicineservices.springboot.main.service;

import com.medicineservices.springboot.main.entities.Role;
import com.medicineservices.springboot.main.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_PATIENT").get();
    }
}
