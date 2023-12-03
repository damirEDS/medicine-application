package com.medicineservices.springboot.security.service;

import com.medicineservices.springboot.main.service.PatientService;
import com.medicineservices.springboot.security.dtos.JwtRequest;
import com.medicineservices.springboot.security.dtos.JwtResponse;
import com.medicineservices.springboot.security.dtos.RegistrationUserDto;
import com.medicineservices.springboot.security.dtos.UserDto;
import com.medicineservices.springboot.main.entities.Patient;
import com.medicineservices.springboot.main.exceptions.AppError;
import com.medicineservices.springboot.security.utils.JwtTokenUtils;
import com.medicineservices.springboot.security.utils.IINValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PatientService patientService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getIin(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = patientService.loadUserByUsername(authRequest.getIin());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if (registrationUserDto.getIin().length() != 12) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "ИИН должен состоять из 12 цифр"), HttpStatus.BAD_REQUEST);
        }
        if (!IINValidator.validateIIN(registrationUserDto.getIin())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Некорректный ИИН"), HttpStatus.BAD_REQUEST);
        }
        if (patientService.findByIin(registrationUserDto.getIin()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным ИИН уже существует"), HttpStatus.BAD_REQUEST);
        }
        Patient patient = patientService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(new UserDto(patient.getId(), patient.getIin(), patient.getEmail(), patient.getFullName(), patient.getPhoneNumber()));
    }
}
