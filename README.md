# Vet Clinic

Сервис ветеринарной клиники

# Цель:

> Управление ветеринарной клиникой, работа с пользователями и питомцами

# Содержание

1. [Название приложения](#vet-clinic)
2. [Содержание](#содержание)
3. [Требования](#требования)
    1. [Фреймворки и утилиты](#фреймворки-и-утилиты)
    2. [Параметры конфигурации](#параметры-конфигурации)
4. [Разработка](#разработка)
    1. [Установка зависимостей и компиляция](#установка-зависимостей-и-компиляция)
    2. [Запуск Spring Boot приложения](#запуск-spring-boot-приложения)
    3. [Сборка](#сборка)
    4. [Запуск Docker Compose](#запуск-docker-compose)
5. [Документация](#документация)
6. [Тестирование](#тестирование)

# Технологии

- Java 17
- Spring Boot 3.0.6
- PostgreSQL

# Требования

## Фреймворки и утилиты

- JDK 17
- Maven

## Параметры конфигурации

| Имя                        | Описание                                 |
|----------------------------|------------------------------------------|
| spring.datasource.url      | Ссылка на подключение к БД               |
| spring.datasource.username | Имя пользователя для подключению к БД    |
| spring.datasource.password | Пароль пользователя для подключению к БД |
| spring.security.jwt-secret | Секрет для генерации JWT                 |

# Разработка

## Установка зависимостей и компиляция

```shell
mvn clean compile
```

## Запуск Spring Boot приложения

```shell
mvn spring-boot:run
```

## Сборка

```shell
mvn package
```

## Запуск Docker Compose
```shell
docker-compose up
```

# Документация
В проекте имеется [Postman коллекция](src/main/resources/postman/vet-clinic.json), с помощью которой можно проводить тестирование API.

## AuthController 
| Метод                                           | HTTP запрос    | Описание                                                  |
|-------------------------------------------------|----------------|-----------------------------------------------------------|
| [**login**](http://localhost:8080/api/v1/auth)  | **POST** /auth | Производит аутентификацию пользователя по логину и паролю |
| [**signup**](http://localhost:8080/api/v1/auth) | **POST** /auth | Создает пользователя с указанными реквизитами             |


## PetController
| Метод                                                             | HTTP запрос                              | Описание                                      |
|-------------------------------------------------------------------|------------------------------------------|-----------------------------------------------|
| [**findById**](http://localhost:8080/api/v1/pets/{id})            | **GET** /pets/{id}                       | Возвращает питомца по ID                      |
| [**findAll**](http://localhost:8080/api/v1/pets)                  | **GET** /pets?page=0&size=10&sort=id,asc | Возвращает список питомцев с фильтрами        |
| [**findAllByUserId**](http://localhost:8080/api/v1/pets/{userId}) | **GET** /pets/{userId}                   | Возвращает список питомцев по ID пользователя |
| [**findAllByType**](http://localhost:8080/api/v1/pets/types)      | **GET** /pets/types                      | Возвращает все виды питомцев со счётчиком     |
| [**create**](http://localhost:8080/api/v1/pets)                   | **POST** /pets                           | Создаёт питомца                               |
| [**update**](http://localhost:8080/api/v1/pets)                   | **PUT** /pets                            | Обновляет питомца                             |
| [**deleteById**](http://localhost:8080/api/v1/pets/{id})          | **DELETE** /pets                         | Удаляет питомца по ID                         |

## TypeController

| Метод                                                     | HTTP запрос                               | Описание                                     |
|-----------------------------------------------------------|-------------------------------------------|----------------------------------------------|
| [**findById**](http://localhost:8080/api/v1/types/{id})   | **GET** /types/{id}                       | Возвращает тип питомца по ID                 |
| [**findAll**](http://localhost:8080/api/v1/types)         | **GET** /types?page=0&size=10&sort=id,asc | Возвращает список типов питомцев с фильтрами |
| [**create**](http://localhost:8080/api/v1/types)          | **POST** /types                           | Создаёт тип питомца                          |
| [**update**](http://localhost:8080/api/v1/types)          | **PUT** /types                            | Обновляет тип питомца                        |
| [**deleteById**](http://localhost:8080/api/v1/types/{id}) | **DELETE** /types                         | Удаляет тип питомца по ID                    |

## UserController

| Метод                                                            | HTTP запрос                               | Описание                                    |
|------------------------------------------------------------------|-------------------------------------------|---------------------------------------------|
| [**findById**](http://localhost:8080/api/v1/users/{id})          | **GET** /users/{id}                       | Возвращает пользователя по ID               |
| [**findAll**](http://localhost:8080/api/v1/users)                | **GET** /users?page=0&size=10&sort=id,asc | Возвращает список пользователей с фильтрами |
| [**create**](http://localhost:8080/api/v1/users)                 | **POST** /users                           | Создаёт пользователя                        |
| [**update**](http://localhost:8080/api/v1/users)                 | **PUT** /users                            | Обновляет пользователя                      |
| [**deleteById**](http://localhost:8080/api/v1/users/{id})        | **DELETE** /users                         | Удаляет пользователя по ID                  |


# Тестирование

## Auth 

### Создание пользователя

API доступно всем
> Можно зарегистрироваться только с ролью USER

**Запрос:**
```
curl --location 'http://localhost:8080/api/v1/auth/signup' \
--header 'Content-Type: application/json' \
--data '{
    "firstName": "Диас",
    "lastName": "Саимов",
    "username": "d.saimov",
    "password": "123456",
    "roles": ["ROLE_USER"]
}'
```

**Ответ:**
```json
{
    "id": 4,
    "username": "d.saimov",
    "firstName": "Диас",
    "lastName": "Саимов",
    "fullName": null,
    "roles": [
        {
            "id": 1,
            "type": "ROLE_USER"
        }
    ]
}
```

### Аутентификация

API доступно всем

**Запрос:**
```
curl --location 'http://localhost:8080/api/v1/auth/login' \
--header 'Content-Type: application/json' \
--data '{
    "username": "a.akhnazarova",
    "password": "qwerty123"
}'
```

**Ответ:**
```json
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLmFraG5hemFyb3ZhIiwiaWF0IjoxNzE0NjY5NTM3LCJleHAiOjE3MTQ2NzEzMzd9.vEr_nldxOihx89v6kizdn8XApbdRMTtw3Ftpm1NUr_mXbDa0tl5tzFkDYfd_Wm9oDE7PeJjv41EGxrwi5J4cfw",
    "id": 1,
    "username": "a.akhnazarova",
    "roles": [
        "ROLE_USER",
        "ROLE_ADMIN"
    ]
}
```

## Pet

### Создание питомца

API доступно `Пользователю`

> Указываем, что питомец принадлежит пользователю с `ID = 1`, при этом имеет `Type = Кошка`

**Запрос:**
```
curl --location 'http://localhost:8080/api/v1/pets' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLmFraG5hemFyb3ZhIiwiaWF0IjoxNzE0NjY5NTM3LCJleHAiOjE3MTQ2NzEzMzd9.vEr_nldxOihx89v6kizdn8XApbdRMTtw3Ftpm1NUr_mXbDa0tl5tzFkDYfd_Wm9oDE7PeJjv41EGxrwi5J4cfw' \
--data '{
    "name": "Путя",
    "typeId": 2,
    "userId": 1
}'
```

**Ответ:**
```json
{
    "id": 13,
    "name": "Путя",
    "type": {
        "id": 2,
        "name": "Кошка",
        "description": null
    },
    "user": {
        "id": 1,
        "username": "a.akhnazarova",
        "firstName": "Айнура",
        "lastName": "Ахназарова",
        "fullName": null,
        "roles": [
            {
                "id": 1,
                "type": "ROLE_USER"
            },
            {
                "id": 2,
                "type": "ROLE_ADMIN"
            }
        ]
    }
}
```

---

### Получение списка питомцев

API доступно `Пользователю`

> Можем указать количество, страницу и сортировку

**Запрос:**

```
curl --location 'http://localhost:8080/api/v1/pets?page=0&size=10&sort=id%2Casc' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLmFraG5hemFyb3ZhIiwiaWF0IjoxNzE0NjY5NTM3LCJleHAiOjE3MTQ2NzEzMzd9.vEr_nldxOihx89v6kizdn8XApbdRMTtw3Ftpm1NUr_mXbDa0tl5tzFkDYfd_Wm9oDE7PeJjv41EGxrwi5J4cfw'
```

**Ответ:**
```json
{
    "content": [
        {
            "id": 1,
            "name": "Барон",
            "type": {
                "id": 1,
                "name": "Собака",
                "description": null
            },
            "user": {
                "id": 1,
                "username": "a.akhnazarova",
                "firstName": "Айнура",
                "lastName": "Ахназарова",
                "fullName": null,
                "roles": [
                    {
                        "id": 1,
                        "type": "ROLE_USER"
                    },
                    {
                        "id": 2,
                        "type": "ROLE_ADMIN"
                    }
                ]
            }
        },
        {
            "id": 2,
            "name": "Бусинка",
            "type": {
                "id": 2,
                "name": "Кошка",
                "description": null
            },
            "user": {
                "id": 1,
                "username": "a.akhnazarova",
                "firstName": "Айнура",
                "lastName": "Ахназарова",
                "fullName": null,
                "roles": [
                    {
                        "id": 1,
                        "type": "ROLE_USER"
                    },
                    {
                        "id": 2,
                        "type": "ROLE_ADMIN"
                    }
                ]
            }
        },
        {
            "id": 3,
            "name": "Орёл",
            "type": {
                "id": 3,
                "name": "Птица",
                "description": null
            },
            "user": {
                "id": 1,
                "username": "a.akhnazarova",
                "firstName": "Айнура",
                "lastName": "Ахназарова",
                "fullName": null,
                "roles": [
                    {
                        "id": 1,
                        "type": "ROLE_USER"
                    },
                    {
                        "id": 2,
                        "type": "ROLE_ADMIN"
                    }
                ]
            }
        },
        {
            "id": 4,
            "name": "Джули",
            "type": {
                "id": 1,
                "name": "Собака",
                "description": null
            },
            "user": {
                "id": 1,
                "username": "a.akhnazarova",
                "firstName": "Айнура",
                "lastName": "Ахназарова",
                "fullName": null,
                "roles": [
                    {
                        "id": 1,
                        "type": "ROLE_USER"
                    },
                    {
                        "id": 2,
                        "type": "ROLE_ADMIN"
                    }
                ]
            }
        },
        {
            "id": 5,
            "name": "Пушок",
            "type": {
                "id": 2,
                "name": "Кошка",
                "description": null
            },
            "user": {
                "id": 1,
                "username": "a.akhnazarova",
                "firstName": "Айнура",
                "lastName": "Ахназарова",
                "fullName": null,
                "roles": [
                    {
                        "id": 1,
                        "type": "ROLE_USER"
                    },
                    {
                        "id": 2,
                        "type": "ROLE_ADMIN"
                    }
                ]
            }
        },
        {
            "id": 6,
            "name": "Убийца",
            "type": {
                "id": 3,
                "name": "Птица",
                "description": null
            },
            "user": {
                "id": 1,
                "username": "a.akhnazarova",
                "firstName": "Айнура",
                "lastName": "Ахназарова",
                "fullName": null,
                "roles": [
                    {
                        "id": 1,
                        "type": "ROLE_USER"
                    },
                    {
                        "id": 2,
                        "type": "ROLE_ADMIN"
                    }
                ]
            }
        },
        {
            "id": 7,
            "name": "Дружок",
            "type": {
                "id": 1,
                "name": "Собака",
                "description": null
            },
            "user": {
                "id": 2,
                "username": "d.suleimenov",
                "firstName": "Диас",
                "lastName": "Сулейменов",
                "fullName": null,
                "roles": [
                    {
                        "id": 1,
                        "type": "ROLE_USER"
                    },
                    {
                        "id": 2,
                        "type": "ROLE_ADMIN"
                    }
                ]
            }
        },
        {
            "id": 8,
            "name": "Гарфилд",
            "type": {
                "id": 2,
                "name": "Кошка",
                "description": null
            },
            "user": {
                "id": 2,
                "username": "d.suleimenov",
                "firstName": "Диас",
                "lastName": "Сулейменов",
                "fullName": null,
                "roles": [
                    {
                        "id": 1,
                        "type": "ROLE_USER"
                    },
                    {
                        "id": 2,
                        "type": "ROLE_ADMIN"
                    }
                ]
            }
        },
        {
            "id": 9,
            "name": "Соколиный глаз",
            "type": {
                "id": 3,
                "name": "Птица",
                "description": null
            },
            "user": {
                "id": 2,
                "username": "d.suleimenov",
                "firstName": "Диас",
                "lastName": "Сулейменов",
                "fullName": null,
                "roles": [
                    {
                        "id": 1,
                        "type": "ROLE_USER"
                    },
                    {
                        "id": 2,
                        "type": "ROLE_ADMIN"
                    }
                ]
            }
        },
        {
            "id": 10,
            "name": "Лапуся",
            "type": {
                "id": 2,
                "name": "Кошка",
                "description": null
            },
            "user": {
                "id": 3,
                "username": "j.barateon",
                "firstName": "Джоффри",
                "lastName": "Баратеон",
                "fullName": null,
                "roles": [
                    {
                        "id": 1,
                        "type": "ROLE_USER"
                    }
                ]
            }
        }
    ],
    "pageable": {
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 2,
    "totalElements": 13,
    "last": false,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "numberOfElements": 10,
    "first": true,
    "empty": false
}
```

---

### Получение всех типов питомцев с количеством

API доступно `Пользователю`

**Запрос:**
```
curl --location 'http://localhost:8080/api/v1/pets/types' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLmFraG5hemFyb3ZhIiwiaWF0IjoxNzE0NjY5NTM3LCJleHAiOjE3MTQ2NzEzMzd9.vEr_nldxOihx89v6kizdn8XApbdRMTtw3Ftpm1NUr_mXbDa0tl5tzFkDYfd_Wm9oDE7PeJjv41EGxrwi5J4cfw' \
--data '{
    "ids": [1,2]   
}'
```

**Ответ:**
```json
{
    "Кошка": 6,
    "Собака": 3
}
```

---

### Получение питомца по ID

API доступно `Пользователю`


**Запрос:**
```
curl --location 'http://localhost:8080/api/v1/pets/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLmFraG5hemFyb3ZhIiwiaWF0IjoxNzE0NjY5NTM3LCJleHAiOjE3MTQ2NzEzMzd9.vEr_nldxOihx89v6kizdn8XApbdRMTtw3Ftpm1NUr_mXbDa0tl5tzFkDYfd_Wm9oDE7PeJjv41EGxrwi5J4cfw'
```

**Ответ:**
```json
{
    "id": 1,
    "name": "Барон",
    "type": {
        "id": 1,
        "name": "Собака",
        "description": null
    },
    "user": {
        "id": 1,
        "username": "a.akhnazarova",
        "firstName": "Айнура",
        "lastName": "Ахназарова",
        "fullName": null,
        "roles": [
            {
                "id": 1,
                "type": "ROLE_USER"
            },
            {
                "id": 2,
                "type": "ROLE_ADMIN"
            }
        ]
    }
}
```

---

### Получение питомцев по ID пользователя

API доступно `Пользователю`

**Запрос:**
```
curl --location 'http://localhost:8080/api/v1/pets/users/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLmFraG5hemFyb3ZhIiwiaWF0IjoxNzE0NjY5NTM3LCJleHAiOjE3MTQ2NzEzMzd9.vEr_nldxOihx89v6kizdn8XApbdRMTtw3Ftpm1NUr_mXbDa0tl5tzFkDYfd_Wm9oDE7PeJjv41EGxrwi5J4cfw'
```

**Ответ:**
```json
[
    {
        "id": 1,
        "name": "Барон",
        "type": {
            "id": 1,
            "name": "Собака",
            "description": null
        },
        "user": {
            "id": 1,
            "username": "a.akhnazarova",
            "firstName": "Айнура",
            "lastName": "Ахназарова",
            "fullName": null,
            "roles": [
                {
                    "id": 1,
                    "type": "ROLE_USER"
                },
                {
                    "id": 2,
                    "type": "ROLE_ADMIN"
                }
            ]
        }
    },
    {
        "id": 2,
        "name": "Бусинка",
        "type": {
            "id": 2,
            "name": "Кошка",
            "description": null
        },
        "user": {
            "id": 1,
            "username": "a.akhnazarova",
            "firstName": "Айнура",
            "lastName": "Ахназарова",
            "fullName": null,
            "roles": [
                {
                    "id": 1,
                    "type": "ROLE_USER"
                },
                {
                    "id": 2,
                    "type": "ROLE_ADMIN"
                }
            ]
        }
    },
    {
        "id": 3,
        "name": "Орёл",
        "type": {
            "id": 3,
            "name": "Птица",
            "description": null
        },
        "user": {
            "id": 1,
            "username": "a.akhnazarova",
            "firstName": "Айнура",
            "lastName": "Ахназарова",
            "fullName": null,
            "roles": [
                {
                    "id": 1,
                    "type": "ROLE_USER"
                },
                {
                    "id": 2,
                    "type": "ROLE_ADMIN"
                }
            ]
        }
    },
    {
        "id": 4,
        "name": "Джули",
        "type": {
            "id": 1,
            "name": "Собака",
            "description": null
        },
        "user": {
            "id": 1,
            "username": "a.akhnazarova",
            "firstName": "Айнура",
            "lastName": "Ахназарова",
            "fullName": null,
            "roles": [
                {
                    "id": 1,
                    "type": "ROLE_USER"
                },
                {
                    "id": 2,
                    "type": "ROLE_ADMIN"
                }
            ]
        }
    },
    {
        "id": 5,
        "name": "Пушок",
        "type": {
            "id": 2,
            "name": "Кошка",
            "description": null
        },
        "user": {
            "id": 1,
            "username": "a.akhnazarova",
            "firstName": "Айнура",
            "lastName": "Ахназарова",
            "fullName": null,
            "roles": [
                {
                    "id": 1,
                    "type": "ROLE_USER"
                },
                {
                    "id": 2,
                    "type": "ROLE_ADMIN"
                }
            ]
        }
    },
    {
        "id": 6,
        "name": "Убийца",
        "type": {
            "id": 3,
            "name": "Птица",
            "description": null
        },
        "user": {
            "id": 1,
            "username": "a.akhnazarova",
            "firstName": "Айнура",
            "lastName": "Ахназарова",
            "fullName": null,
            "roles": [
                {
                    "id": 1,
                    "type": "ROLE_USER"
                },
                {
                    "id": 2,
                    "type": "ROLE_ADMIN"
                }
            ]
        }
    },
    {
        "id": 13,
        "name": "Путя",
        "type": {
            "id": 2,
            "name": "Кошка",
            "description": null
        },
        "user": {
            "id": 1,
            "username": "a.akhnazarova",
            "firstName": "Айнура",
            "lastName": "Ахназарова",
            "fullName": null,
            "roles": [
                {
                    "id": 1,
                    "type": "ROLE_USER"
                },
                {
                    "id": 2,
                    "type": "ROLE_ADMIN"
                }
            ]
        }
    }
]
```

---

### Обновление питомца

API доступно `Пользователю`

**Запрос:**
```
curl --location --request PUT 'http://localhost:8080/api/v1/pets' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLmFraG5hemFyb3ZhIiwiaWF0IjoxNzE0NjY5NTM3LCJleHAiOjE3MTQ2NzEzMzd9.vEr_nldxOihx89v6kizdn8XApbdRMTtw3Ftpm1NUr_mXbDa0tl5tzFkDYfd_Wm9oDE7PeJjv41EGxrwi5J4cfw' \
--data '{
    "id": 13,
    "name": "Чаки",
    "typeId": 3,
    "userId": 1
}'
```

**Ответ:**
```json
{
    "id": 13,
    "name": "Чаки",
    "type": {
        "id": 3,
        "name": "Птица",
        "description": null
    },
    "user": {
        "id": 1,
        "username": "a.akhnazarova",
        "firstName": "Айнура",
        "lastName": "Ахназарова",
        "fullName": null,
        "roles": [
            {
                "id": 1,
                "type": "ROLE_USER"
            },
            {
                "id": 2,
                "type": "ROLE_ADMIN"
            }
        ]
    }
}
```

---

### Удаление питомца по ID

API доступно `Пользователю`

**Запрос:**
```
curl --location --request DELETE 'http://localhost:8080/api/v1/pets/13' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLmFraG5hemFyb3ZhIiwiaWF0IjoxNzE0NjY5NTM3LCJleHAiOjE3MTQ2NzEzMzd9.vEr_nldxOihx89v6kizdn8XApbdRMTtw3Ftpm1NUr_mXbDa0tl5tzFkDYfd_Wm9oDE7PeJjv41EGxrwi5J4cfw'
```

---

## Type

### Создание типа питомца

API доступно `Пользователю`

**Запрос:**
```
curl --location 'http://localhost:8080/api/v1/types' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLmFraG5hemFyb3ZhIiwiaWF0IjoxNzE0NjY5NTM3LCJleHAiOjE3MTQ2NzEzMzd9.vEr_nldxOihx89v6kizdn8XApbdRMTtw3Ftpm1NUr_mXbDa0tl5tzFkDYfd_Wm9oDE7PeJjv41EGxrwi5J4cfw' \
--data '{
    "name": "Черепаха",
    "description": "Отряд пресмыкающихся"
}'
```

**Ответ:**
```json
{
    "id": 4,
    "name": "Черепаха",
    "description": "Отряд пресмыкающихся"
}
```

### Получение списка типов питомцев

API доступно `Пользователю`

**Запрос:**
```
curl --location 'http://localhost:8080/api/v1/types?page=0&size=10&sort=id%2Casc' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLmFraG5hemFyb3ZhIiwiaWF0IjoxNzE0NjcxMzYyLCJleHAiOjE3MTQ2NzMxNjJ9.QjSNCA8dQ1SyJy2QGQxBoIJAfj5o4UuQblQvvzRa9Fh_OJARqYz4MKvnuzJB_bclDsGiNQZdswOz9J0-DAbt-g'
```

**Ответ:**
```json
{
    "content": [
        {
            "id": 1,
            "name": "Собака",
            "description": null
        },
        {
            "id": 2,
            "name": "Кошка",
            "description": null
        },
        {
            "id": 3,
            "name": "Птица",
            "description": null
        },
        {
            "id": 4,
            "name": "Черепаха",
            "description": "Отряд пресмыкающихся"
        }
    ],
    "pageable": {
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 4,
    "last": true,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "numberOfElements": 4,
    "first": true,
    "empty": false
}
```

---

### Получение типа питомца по ID

API доступно `Пользователю`

**Запрос:**
```
curl --location 'http://localhost:8080/api/v1/types/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLmFraG5hemFyb3ZhIiwiaWF0IjoxNzE0NjcxMzYyLCJleHAiOjE3MTQ2NzMxNjJ9.QjSNCA8dQ1SyJy2QGQxBoIJAfj5o4UuQblQvvzRa9Fh_OJARqYz4MKvnuzJB_bclDsGiNQZdswOz9J0-DAbt-g'
```

**Ответ:**
```json
{
    "id": 1,
    "name": "Собака",
    "description": null
}
```

---

### Обновление типа питомца

API доступно `Пользователю`

**Запрос:**
```
curl --location --request PUT 'http://localhost:8080/api/v1/types' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLmFraG5hemFyb3ZhIiwiaWF0IjoxNzE0NjcxMzYyLCJleHAiOjE3MTQ2NzMxNjJ9.QjSNCA8dQ1SyJy2QGQxBoIJAfj5o4UuQblQvvzRa9Fh_OJARqYz4MKvnuzJB_bclDsGiNQZdswOz9J0-DAbt-g' \
--data '{
    "id": 4,
    "name": "Рыба",
    "description": "Водные"
}'
```

**Ответ:**
```json
{
    "id": 4,
    "name": "Рыба",
    "description": "Водные"
}
```

---

### Удаление типа питомца по ID

API доступно `Пользователю`

**Запрос:**
```
curl --location --request DELETE 'http://localhost:8080/api/v1/types/4' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLmFraG5hemFyb3ZhIiwiaWF0IjoxNzE0NjcxMzYyLCJleHAiOjE3MTQ2NzMxNjJ9.QjSNCA8dQ1SyJy2QGQxBoIJAfj5o4UuQblQvvzRa9Fh_OJARqYz4MKvnuzJB_bclDsGiNQZdswOz9J0-DAbt-g'
```

---

## User

### Создание пользователя

API доступно `Администратору`

**Запрос:**
```
curl --location 'http://localhost:8080/api/v1/users' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLmFraG5hemFyb3ZhIiwiaWF0IjoxNzE0NjcxMzYyLCJleHAiOjE3MTQ2NzMxNjJ9.QjSNCA8dQ1SyJy2QGQxBoIJAfj5o4UuQblQvvzRa9Fh_OJARqYz4MKvnuzJB_bclDsGiNQZdswOz9J0-DAbt-g' \
--data '{
    "firstName": "Азамат",
    "lastName": "Мусагалиев",
    "username": "a.musagaliyev",
    "password": "123456"
}
'
```

**Ответ:**
```json
{
    "id": 5,
    "username": "a.musagaliyev",
    "firstName": "Азамат",
    "lastName": "Мусагалиев",
    "fullName": null,
    "roles": [
        {
            "id": 1,
            "type": "ROLE_USER"
        }
    ]
}
```

---

### Получение списка пользователей

API доступно `Администратору`

**Запрос:**
```
curl --location 'http://localhost:8080/api/v1/users?page=0&size=10&sort=id%2Casc' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLmFraG5hemFyb3ZhIiwiaWF0IjoxNzE0NjcxMzYyLCJleHAiOjE3MTQ2NzMxNjJ9.QjSNCA8dQ1SyJy2QGQxBoIJAfj5o4UuQblQvvzRa9Fh_OJARqYz4MKvnuzJB_bclDsGiNQZdswOz9J0-DAbt-g'
```

**Ответ:**
```json
{
    "content": [
        {
            "id": 1,
            "username": "a.akhnazarova",
            "firstName": "Айнура",
            "lastName": "Ахназарова",
            "fullName": null,
            "roles": [
                {
                    "id": 1,
                    "type": "ROLE_USER"
                },
                {
                    "id": 2,
                    "type": "ROLE_ADMIN"
                }
            ]
        },
        {
            "id": 2,
            "username": "d.suleimenov",
            "firstName": "Диас",
            "lastName": "Сулейменов",
            "fullName": null,
            "roles": [
                {
                    "id": 1,
                    "type": "ROLE_USER"
                },
                {
                    "id": 2,
                    "type": "ROLE_ADMIN"
                }
            ]
        },
        {
            "id": 3,
            "username": "j.barateon",
            "firstName": "Джоффри",
            "lastName": "Баратеон",
            "fullName": null,
            "roles": [
                {
                    "id": 1,
                    "type": "ROLE_USER"
                }
            ]
        },
        {
            "id": 4,
            "username": "d.saimov",
            "firstName": "Диас",
            "lastName": "Саимов",
            "fullName": null,
            "roles": [
                {
                    "id": 1,
                    "type": "ROLE_USER"
                }
            ]
        },
        {
            "id": 5,
            "username": "a.musagaliyev",
            "firstName": "Азамат",
            "lastName": "Мусагалиев",
            "fullName": null,
            "roles": [
                {
                    "id": 1,
                    "type": "ROLE_USER"
                }
            ]
        }
    ],
    "pageable": {
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 5,
    "last": true,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "numberOfElements": 5,
    "first": true,
    "empty": false
}
```

---

### Получение пользователя по ID

API доступно `Пользователю`

**Запрос:**
```
curl --location 'http://localhost:8080/api/v1/users/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLmFraG5hemFyb3ZhIiwiaWF0IjoxNzE0NjcxMzYyLCJleHAiOjE3MTQ2NzMxNjJ9.QjSNCA8dQ1SyJy2QGQxBoIJAfj5o4UuQblQvvzRa9Fh_OJARqYz4MKvnuzJB_bclDsGiNQZdswOz9J0-DAbt-g'
```

**Ответ:**
```json
{
    "id": 1,
    "username": "a.akhnazarova",
    "firstName": "Айнура",
    "lastName": "Ахназарова",
    "fullName": null,
    "roles": [
        {
            "id": 1,
            "type": "ROLE_USER"
        },
        {
            "id": 2,
            "type": "ROLE_ADMIN"
        }
    ]
}
```

---

### Обновление пользователя

API доступно `Пользователю`

**Запрос:**
```
curl --location --request PUT 'http://localhost:8080/api/v1/users' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLmFraG5hemFyb3ZhIiwiaWF0IjoxNzE0NjcxMzYyLCJleHAiOjE3MTQ2NzMxNjJ9.QjSNCA8dQ1SyJy2QGQxBoIJAfj5o4UuQblQvvzRa9Fh_OJARqYz4MKvnuzJB_bclDsGiNQZdswOz9J0-DAbt-g' \
--data '{
    "id": 4,
    "firstName": "Дуэйн",
    "lastName": "Скала",
    "username": "johnson",
    "password": "123456",
    "roles": []
}
'
```

**Ответ:**
```json
{
    "id": 4,
    "username": "d.saimov",
    "firstName": "Дуэйн",
    "lastName": "Скала",
    "fullName": null,
    "roles": [
        {
            "id": 1,
            "type": "ROLE_USER"
        }
    ]
}
```

---

### Удаление пользователя по ID

API доступно `Администратору`

**Запрос:**
```
curl --location --request DELETE 'http://localhost:8080/api/v1/users/4' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLmFraG5hemFyb3ZhIiwiaWF0IjoxNzE0NjcxMzYyLCJleHAiOjE3MTQ2NzMxNjJ9.QjSNCA8dQ1SyJy2QGQxBoIJAfj5o4UuQblQvvzRa9Fh_OJARqYz4MKvnuzJB_bclDsGiNQZdswOz9J0-DAbt-g'
```

---