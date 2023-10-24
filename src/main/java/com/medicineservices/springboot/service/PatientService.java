package com.medicineservices.springboot.service;

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
import com.medicineservices.springboot.repositories.UserRepository;
import com.medicineservices.springboot.utils.JwtTokenUtils;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService implements UserDetailsService {
    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Patient> findByIIN(String iin) {
        return userRepository.findByIIN(iin);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String iin) throws UsernameNotFoundException {
        Patient patient = findByIIN(iin).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", iin)
        ));
        return new org.springframework.security.core.userdetails.User(
                patient.getIin(),
                patient.getPassword(),
                patient.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public Patient createNewUser(RegistrationUserDto registrationUserDto) {
        Patient patient = new Patient();
        patient.setIin(registrationUserDto.getIin());
        patient.setEmail(registrationUserDto.getEmail());
        patient.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        patient.setRoles(List.of(roleService.getUserRole()));
        return userRepository.save(patient);
    }
}
