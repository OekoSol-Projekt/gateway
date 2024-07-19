-- V1__Initial_Setup.sql
CREATE TABLE roles (
                       id BIGINT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL
);

CREATE TABLE users (
                       id BIGINT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       enabled BOOLEAN NOT NULL,
                       role_id BIGINT,
                       CONSTRAINT fk_role FOREIGN KEY(role_id) REFERENCES roles(id)
);
