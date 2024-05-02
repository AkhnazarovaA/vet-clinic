INSERT INTO public.users (id, first_name, last_name, username, "password")
VALUES (1, 'Айнура', 'Ахназарова', 'a.akhnazarova', '$2a$10$Ccuq2wq8b7ETG5bsqo35Oe3TA.BSwHxYjql7mZhFFsT6oZCp8cRZK');
INSERT INTO public.users (id, first_name, last_name, username, "password")
VALUES (2, 'Диас', 'Сулейменов', 'd.suleimenov', '$2a$10$Ccuq2wq8b7ETG5bsqo35Oe3TA.BSwHxYjql7mZhFFsT6oZCp8cRZK');
INSERT INTO public.users (id, first_name, last_name, username, "password")
VALUES (3, 'Джоффри', 'Баратеон', 'j.barateon', '$2a$10$Ccuq2wq8b7ETG5bsqo35Oe3TA.BSwHxYjql7mZhFFsT6oZCp8cRZK');

SELECT setval('seq_users', max(id)) FROM public.users;