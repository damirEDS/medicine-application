package com.medicineservices.springboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.medicineservices.springboot.entities.Patient;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Patient, Long> {
    Optional<Patient> findByIIN(String iin);
}
