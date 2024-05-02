CREATE SEQUENCE public.seq_users
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE public.users
(
    id         BIGINT       NOT NULL,
    first_name VARCHAR(50)  NOT NULL,
    last_name  VARCHAR(50)  NOT NULL,
    username   VARCHAR(20)  NOT NULL,
    password   VARCHAR(100) NOT NULL,

    CONSTRAINT pk_users          PRIMARY KEY (id),
    CONSTRAINT uq_users_username UNIQUE (username)
);