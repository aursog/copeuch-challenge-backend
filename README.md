# Challenge backend Copeuch

![technology_java](https://img.shields.io/badge/Java-17-yellow)
![framework](https://img.shields.io/badge/SpringBoot-2.7.0-blue)
![docto](https://img.shields.io/badge/swagger-2.9.2-orange)

Challenge para puesto de Technical Leader en Copeuch.

## Descripción

Esta api contiene el backend del desafío técnico, el cuál consiste en dos partes:

1.- Una API Backend desarrollada en Java

2.- Un Frontend desarrollado en React + Redux

El challenge sigue una arquitectura tradicional MVC usando conceptos de SOLID.

Para el caso se presenta la documentación para la API en Java, versión 17. Se deberá tomar en cuenta que
esta API tiene integración con JWT, en la sección **Usuarios de prueba** se adjunta un listado de usuarios
que se encuentran registrados en la base de datos para poder probar.

### Usuarios de prueba

Para realizar pruebas con usuarios, se podrán utilizar los siguientes usuarios:

| Usuario              | Password | Rol     |
|----------------------|----------|---------|
| jsmith@copeuch.cl    | test1    | Miembro |
| janice@copeuch.cl    | test2    | Miembro |
| samuel@copeuch.cl    | test3    | Miembro |
| samantha@copeuch.cl  | test4    | Miembro |
| andrea@copeuch.cl    | test5    | Miembro |
| peter@copeuch.cl     | test6    | Miembro |
| ruth@copeuch.cl      | test7    | Miembro |
| rvalencia@copeuch.cl | test8    | Miembro |
| sally@copeuch.cl     | test9    | Miembro |
| swick@copeuch.cl     | test10   | Admin   |


La diferencia entre un usuario de tipo **miembro** y un usuario del tipo **admin**, es al momento de operar
con las diferentes tareas. Lo cual será detallado en el apartado de **Consideraciones y Uso**.

Los usuarios ya se encuentran insertados en la base de datos y podrán ser visualizados en la respectiva
migración (**V1.1.__initial_load_of_users.sql**).

### End-point

Para esta aplicación backend, existen 3 end-points principales:

| Recurso           | Requiere Autenticación | Detalle                                                                                 |
|-------------------|------------------------|-----------------------------------------------------------------------------------------|
| /api/ping         | No                     | Health check de la app                                                                  |
| /api/authenticate | No                     | Permite obtener el JWT según los datos de usuario y contraseña de los usuarios listados |
| /api/task/*       | Si                     | Operaciones GET, POST, PATCH y DELETE con la lógica de las tasks.                       |

## Requerimientos

* Java 17
* Gradle 7.6.1
* Lombok
* Spring Boot 2.7.0
* DB postgres
* Flyway

## Instalación

Se recomienda instalar el plugin Lombok en el IDE.

Se debe tener instalada la máquina virtual de java 17 (zulu):

```bash
sdk install java 17.0.6-zulu
```

Clonar el repositorio y ejecutar:

```bash
./gradlew bootRun
```

Para abrir en método **debug**:

```bash
./gradlew bootRun --debug-jvm
```

Para correr los test

```bash
./gradlew test
```

## Consideraciones y Uso

Para el challenge se utiliza integración con JWT a través de _WebSecurity_ y _SecurityFilterChain_, esto
de manera de poder proveer una API que se encuentre asegurada tras un token que se obtiene a través de un
usuario y contraseña, debidamente registrado en el sistema.

Si bien la integración es simple, las contraseñas se encuentran encriptadas y los usuarios (para este caso)
se encuentran ya registrados. La gestión de usuarios escapa del objetivo del challenge y por eso no se
consideró trabajar.

La otra consideración que se tomó, es respecto a los roles de los usuarios. En el sistema existen dos roles
de usuarios:

- Miembro
- Admin

La diferencia es que un miembro solo puede ver y gestionar sus propias tareas. En cambio un usuario administrador
puede gestionar y listar todas las tareas del sistema.

Para comenzar, realizamos el llamado al endpoint de authenticate con los datos de alguno de los usuarios:

<img width="1561" alt="Captura de Pantalla 2023-05-28 a la(s) 00 39 09" src="https://github.com/aursog/copeuch-challenge-backend/assets/5913615/91de5a17-5eff-4785-89da-e09ab909df20">

```bash
curl -X POST "http://localhost:8080/api/authenticate" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"password\": \"test10\", \"username\": \"swick@copeuch.cl\"}"
```

Este end-point responderá con un objeto json con el jwt_token válido para ese usuario.

Luego, dependiendo del perfil, podrá acceder a las distintas acciones de las task (GET, POST, PATCH, DELETE)
pasando el token en el atributo **Authorization** del Header como un token **Bearer**.

<img width="1633" alt="Captura de Pantalla 2023-05-28 a la(s) 00 39 46" src="https://github.com/aursog/copeuch-challenge-backend/assets/5913615/2d6e3ef8-92df-4b96-8e39-d21fcd7abcb7">


```bash
curl -X GET "http://localhost:8080/api/cards" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzd2lja0Bjb3BldWNoLmNsIiwiaWF0IjoxNjg1MjQ2MzkwLCJleHAiOjE2ODUzMzI3OTB9.Qy4sNdis37x7Ifed01gmQZ0OhGVL4FRdgSTmICnFlHab3fjmjHk5SQnn04s9ZL9_8jGQetUBh-Xq7zYZBOwuKw"
```

<img width="1542" alt="Captura de Pantalla 2023-05-28 a la(s) 00 40 02" src="https://github.com/aursog/copeuch-challenge-backend/assets/5913615/986e784c-e6ee-4200-9e07-15a28ce63907">