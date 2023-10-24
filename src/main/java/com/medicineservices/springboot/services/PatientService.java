package com.medicineservices.springboot.services;

import com.medicineservices.springboot.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.medicineservices.springboot.dtos.RegistrationUserDto;
import com.medicineservices.springboot.entities.Patient;
import com.medicineservices.springboot.repositories.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService implements UserDetailsService {
    private final PatientRepository patientRepository;
    private final RoleRepository roleRepository;

    public Optional<Patient> findByIin(String iin) {
        return patientRepository.findByIin(iin);
    }
}
