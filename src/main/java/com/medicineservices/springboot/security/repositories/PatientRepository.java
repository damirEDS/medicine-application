package com.medicineservices.springboot.security.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.medicineservices.springboot.security.entities.Patient;

import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
    Optional<Patient> findByIin(String iin);
}
