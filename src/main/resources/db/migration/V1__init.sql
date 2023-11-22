CREATE TABLE patients (
                          id                    bigserial,
                          iin                   varchar(12) not null unique,
                          password              varchar(80) not null,
                          email                 varchar(50) unique,
                          --  fullname              varchar(80) not null,
                          --  gender                boolean not null, -- Changed the data type to boolean
                          --  dateOfBirth           varchar(6) not null,
                          --  phoneNumber           varchar(11) not null,
                          primary key (id)
);


create table roles (
                      id                    serial,
                      name                  varchar(50) not null,
                      primary key (id)
);

CREATE TABLE patients_roles (
                                patient_id               bigint not null,
                                role_id                  int not null,
                                primary key (patient_id, role_id),
                                foreign key (patient_id) references patients (id),
                                foreign key (role_id) references roles (id)
);

insert into roles (name)
values
    ('ROLE_PATIENT'), ('ROLE_ADMIN');

insert into patients (iin, password, email)
values
('040310501648', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
('050310501648', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com');

insert into patients_roles (patient_id, role_id)
values
(1, 1),
(2, 2);