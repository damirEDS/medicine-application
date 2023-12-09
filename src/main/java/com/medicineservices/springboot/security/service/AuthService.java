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
import com.medicineservices.springboot.translation.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PatientService patientService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final MessageSource messageSource;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getIin(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            Locale currentLocale = LocaleContextHolder.getLocale();
            String errorMessage = messageSource.getMessage("error.invalid.credentials", null, "Invalid credentials", currentLocale);
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), errorMessage), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = patientService.loadUserByUsername(authRequest.getIin());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String errorPasswordsNotMatch = messageSource.getMessage("error.passwords.not.match", null, "Passwords do not match", currentLocale);
        String errorPasswordPattern = messageSource.getMessage("error.password.pattern", null, "Password must contain at least one uppercase letter, one digit, and be between 8 to 20 characters long", currentLocale);
        String errorIinLength = messageSource.getMessage("error.iin.length", null, "IIN must consist of 12 digits", currentLocale);
        String errorInvalidIin = messageSource.getMessage("error.invalid.iin", null, "Invalid IIN", currentLocale);
        String errorExistingIin = messageSource.getMessage("error.existing.iin", null, "User with specified IIN already exists", currentLocale);
        String errorExistingEmail = messageSource.getMessage("error.existing.email", null, "User with specified email already exists", currentLocale);
        String errorExistingPhone = messageSource.getMessage("error.existing.phone", null, "User with specified phone number already exists", currentLocale);

        String passwordPattern = "^(?=.*[0-9])(?=.*[A-Z]).{8,20}$";

        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), errorPasswordsNotMatch), HttpStatus.BAD_REQUEST);
        }
        if (!registrationUserDto.getPassword().matches(passwordPattern)) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), errorPasswordPattern), HttpStatus.BAD_REQUEST);
        }
        if (registrationUserDto.getIin().length() != 12) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), errorIinLength), HttpStatus.BAD_REQUEST);
        }
        if (!IINValidator.validateIIN(registrationUserDto.getIin())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), errorInvalidIin), HttpStatus.BAD_REQUEST);
        }
        if (patientService.findByIin(registrationUserDto.getIin()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), errorExistingIin), HttpStatus.BAD_REQUEST);
        }
        if (patientService.findByEmail(registrationUserDto.getEmail()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), errorExistingEmail), HttpStatus.BAD_REQUEST);
        }
        if (patientService.findByPhone(registrationUserDto.getPhoneNumber()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), errorExistingPhone), HttpStatus.BAD_REQUEST);
        }
        Patient patient = patientService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(new UserDto(patient.getId(), patient.getIin(), patient.getEmail(), patient.getFullName(), patient.getPhoneNumber()));
    }
}
