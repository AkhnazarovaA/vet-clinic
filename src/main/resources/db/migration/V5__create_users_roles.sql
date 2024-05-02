CREATE TABLE public.users_roles
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,

    CONSTRAINT pk_users_roles   PRIMARY KEY (role_id, user_id),
    CONSTRAINT fk_users_role_id FOREIGN KEY (role_id) REFERENCES public.roles(id),
    CONSTRAINT fk_users_user_id FOREIGN KEY (user_id) REFERENCES public.users(id)
);
