CREATE SEQUENCE seq_pets
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE pets
(
    id      BIGINT      NOT NULL,
    name    VARCHAR(50) NOT NULL,
    type_id BIGINT      NOT NULL,
    user_id BIGINT      NOT NULL,

    CONSTRAINT pk_pets         PRIMARY KEY (id),
    CONSTRAINT fk_pets_type_id FOREIGN KEY (type_id) REFERENCES public.types(id),
    CONSTRAINT fk_pets_user_id FOREIGN KEY (user_id) REFERENCES public.users(id)
);