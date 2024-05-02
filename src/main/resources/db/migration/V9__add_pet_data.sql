INSERT INTO public.pets (id, "name", type_id, user_id) VALUES (1, 'Барон', 1, 1);
INSERT INTO public.pets (id, "name", type_id, user_id) VALUES (2, 'Бусинка', 2, 1);
INSERT INTO public.pets (id, "name", type_id, user_id) VALUES (3, 'Орёл', 3, 1);
INSERT INTO public.pets (id, "name", type_id, user_id) VALUES (4, 'Джули', 1, 1);
INSERT INTO public.pets (id, "name", type_id, user_id) VALUES (5, 'Пушок', 2, 1);
INSERT INTO public.pets (id, "name", type_id, user_id) VALUES (6, 'Убийца', 3, 1);
INSERT INTO public.pets (id, "name", type_id, user_id) VALUES (7, 'Дружок', 1, 2);
INSERT INTO public.pets (id, "name", type_id, user_id) VALUES (8, 'Гарфилд', 2, 2);
INSERT INTO public.pets (id, "name", type_id, user_id) VALUES (9, 'Соколиный глаз', 3, 2);
INSERT INTO public.pets (id, "name", type_id, user_id) VALUES (10, 'Лапуся', 2, 3);
INSERT INTO public.pets (id, "name", type_id, user_id) VALUES (11, 'Бесхвостый', 2, 3);
INSERT INTO public.pets (id, "name", type_id, user_id) VALUES (12, 'Земля-воздух', 3, 3);

SELECT setval('seq_pets', max(id)) FROM public.pets;