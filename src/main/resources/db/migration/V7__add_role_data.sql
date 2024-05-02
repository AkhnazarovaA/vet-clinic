INSERT INTO public.roles (id, "type") VALUES (1, 'ROLE_USER');
INSERT INTO public.roles (id, "type") VALUES (2, 'ROLE_ADMIN');

SELECT setval('seq_roles', max(id)) FROM public.roles;