CREATE SEQUENCE seq_types
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE public.types
(
    id          BIGINT      NOT NULL,
    name        VARCHAR(50) NOT NULL,
    description VARCHAR(50),

    CONSTRAINT pk_types      PRIMARY KEY (id),
    CONSTRAINT uq_types_name UNIQUE (name)
);