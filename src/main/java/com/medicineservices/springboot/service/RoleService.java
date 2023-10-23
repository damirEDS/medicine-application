package com.medicineservices.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.medicineservices.springboot.entities.Role;
import com.medicineservices.springboot.entities.Patient;
import com.medicineservices.springboot.repositories.RoleRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_PATIENT").get();
    }
}
