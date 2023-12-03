CREATE TABLE doctors
(
    id             BIGSERIAL,
    iin            VARCHAR(12)  NOT NULL UNIQUE,
    password       VARCHAR(80)  NOT NULL,
    email          VARCHAR(50) UNIQUE,
    name           VARCHAR(255) NOT NULL,
    specialization VARCHAR(255) NOT NULL,
    degree         VARCHAR(255) NOT NULL,
    state          VARCHAR(255) NOT NULL,
    city           VARCHAR(255) NOT NULL,
    phoneNumber    VARCHAR(11)  NOT NULL,
    PRIMARY KEY (id)
);
insert into roles (name)
values ('ROLE_PATIENT'),
       ('ROLE_ADMIN');

insert into patients (iin, password, email, fullname, phone_number)
values ('740512402091', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com', 'Test Test Test','87787787878'),
       ('050310501648', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com', 'Test Test Test','87007007070');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);