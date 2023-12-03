package com.medicineservices.springboot.main.entities;

import javax.persistence.*;

import com.medicineservices.springboot.main.entities.Role;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name="doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "iin", length = 12, unique = true)
    private String iin;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "degree")
    private String degree;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

}