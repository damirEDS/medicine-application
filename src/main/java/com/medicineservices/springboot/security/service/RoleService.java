package com.medicineservices.springboot.security.service;

import com.medicineservices.springboot.security.entities.Role;
import com.medicineservices.springboot.security.repositories.RoleRepository;
import com.medicineservices.springboot.security.entities.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_PATIENT").get();
    }
}
