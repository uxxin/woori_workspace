DROP DATABASE IF EXISTS security_exam;

CREATE DATABASE security_exam;

use security_exam;

DROP TABLE IF EXISTS users;

create table users (
    id bigint AUTO_INCREMENT,
    username varchar(255) not null,
    password varchar(255) not null,
    primary key (id)
);

DROP TABLE IF EXISTS role;

create table role (
    id bigint AUTO_INCREMENT,
    name varchar(255) not null,
    primary key (id)
);

DROP TABLE IF EXISTS authority;

create table authority (
    id bigint AUTO_INCREMENT,
    name varchar(255) not null,
    primary key (id)
);

DROP TABLE user_role IF EXISTS;

create table user_role (
    user_id bigint,
    role_id bigint,
    primary key (user_id, role_id),
    foreign key (user_id) references users(id),
    foreign key (role_id) references role(id)
);

DROP TABLE IF EXISTS role_authority;

create table role_authority (
    role_id bigint not null,
    authority_id bigint not null,
    primary key (role_id, authority_id),
    foreign key (role_id) references role(id),
    foreign key (authority_id) references authority(id)
);