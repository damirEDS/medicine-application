CREATE TABLE patients
(
    id       bigserial,
    iin      varchar(12) not null unique,
    password varchar(80) not null,
    email    varchar(50) unique,
    fullname varchar(80) not null,
    --  gender                boolean not null, -- Changed the data type to boolean
    --  dateOfBirth           varchar(6) not null,
    phone_number varchar(11) not null,
    primary key (id)
);