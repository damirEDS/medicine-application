package com.medicineservices.springboot.main.service;

import lombok.RequiredArgsConstructor;
import com.medicineservices.springboot.security.dtos.RegistrationUserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.medicineservices.springboot.main.entities.Patient;
import com.medicineservices.springboot.main.repositories.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService implements UserDetailsService {
    private PatientRepository patientRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public PatientService(PatientRepository patientRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    public void setUserRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Patient> findByIin(String iin) {
        return patientRepository.findByIin(iin);
    }
    public Optional<Patient> findByEmail(String email) {
        return patientRepository.findByEmail(email);
    }
    public Optional<Patient> findByPhone(String phoneNumber) {
        return patientRepository.findByPhoneNumber(phoneNumber);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String iin) throws UsernameNotFoundException {
        Patient patient = findByIin(iin).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Указанный ИИН '%s' не найден. Проверьте правильность введенных данных.", iin)
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
        patient.setFullName(registrationUserDto.getFullname());
        patient.setPhoneNumber(registrationUserDto.getPhoneNumber());
        patient.setRoles(List.of(roleService.getUserRole()));
        return patientRepository.save(patient);
    }
}
