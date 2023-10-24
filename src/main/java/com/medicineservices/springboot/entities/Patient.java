package com.medicineservices.springboot.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.Collection;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "iin")
    private String iin;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "is_male")
    private boolean isMale;

    @Column(name = "first_name") // Добавили имя
    private String firstName;

    @Column(name = "last_name") // Добавили фамилию
    private String lastName;

    @Column(name = "middle_name") // Добавили отчество
    private String middleName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;
}
