INSERT INTO public.types (id, "name", description) VALUES (1, 'Собака', null);
INSERT INTO public.types (id, "name", description) VALUES (2, 'Кошка', null);
INSERT INTO public.types (id, "name", description) VALUES (3, 'Птица', null);

SELECT setval('seq_types', max(id)) FROM public.types;