package com.medicineservices.springboot.entities;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;
//import javax.validation.constraints.Pattern;
import java.util.Collection;

@Entity
@Data
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "iin", length = 12, unique = true)
//    @Pattern(regexp = "\\d{12}", message = "ИИН должен состоять из 12 цифр")
    private String iin;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

//    @Column(name = "full_name")
//    private String fullName;
//
//    @Column(name = "gender")
//    private Boolean gender;
//
//    @Column(name = "date_of_birth")
//    private Date dateOfBirth;
//
//    @Column(name = "phone_number")
//    private String phoneNumber;


    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;
}
