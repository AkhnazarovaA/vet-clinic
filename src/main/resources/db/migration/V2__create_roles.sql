CREATE SEQUENCE seq_roles
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE roles
(
    id   BIGINT      NOT NULL,
    type VARCHAR(20) NOT NULL,

    CONSTRAINT pk_roles      PRIMARY KEY (id),
    CONSTRAINT uq_roles_type UNIQUE (type)
);