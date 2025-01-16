# PRUEBA TECNICA DE DUX SOFTWARE

### Autor: Precopio Kiara

### Descripción
Este proyecto es una prueba técnica para la empresa Dux Software, en la cual se debe desarrollar, utilizando
Spring Boot 3 y Java 17, una API para gestionar información de equipos de fútbol.

### Requisitos
* Esta aplicación debe contar con al menos una prueba unitaria a algún servicio, utilizando mocks para la base de datos,
* La persistencia tiene que lograrse con H2 en memoria,
* Se debe proporcionar un dockerfile para construir la imagen de la aplicación,
* Se debe utilizar JWT para la autenticación.

### Endpoints
#### Sin JWT
* **POST /auth/login**: recibe un usuario y contraseña, y devuelve un token JWT.
  * Request body:
  ```json
    {
      "username": "username",
      "password": "password"
    }
    ```

#### Con JWT
* **GET /equipos**: obtiene la lista de equipos.
  * No posee parámetros ni request body.


* **GET /equipos/{id}**: obtiene un equipo por id.
  * Recibe un _path variable_ del id de equipo.


* **GET /equipos/buscar**: obtiene un equipo por nombre.
  * Recibe un _query param_ del nombre de equipo.


* **POST /equipos**: crea un equipo.
  * Recibe un request body:
  ```json
    {
      "nombre": "nombre",
      "liga": "liga",
      "pais": "pais"
    }
    ```
* **PUT /equipos/{id}**: actualiza un equipo por id.
  * Recibe un _path variable_ con el id del equipo y el request body:
  ```json
    {
      "nombre": "nombre",
      "liga": "liga",
      "pais": "pais"
    }
    ```
* **DELETE /equipos/{id}**: elimina un equipo por id.
  * Recibe un _path variable_ con el id del equipo.

### Respuestas

* **200 OK**: se devuelve cuando la operación se realizó correctamente.
* **201 Created**: se devuelve cuando se crea un recurso.
* **400 Bad Request**: se devuelve cuando la petición es incorrecta.
* **401 Unauthorized**: se devuelve cuando no se envía un token JWT o el token es incorrecto.
* **404 Not Found**: se devuelve cuando no se encuentra el recurso solicitado.
* **500 Internal Server Error**: se devuelve cuando ocurre un error en el servidor.

### Tests

#### Pruebas unitarias
* **Servicio de login**: Verifica el servicio de login.
  * Mock del repositorio de usuarios.
  * Verifica un token generado válido.
  * Verifica el ingreso de un usuario con contraseña incorrecta.
  * Verifica el ingreso de un usuario inexistente.
* **Servicio de equipos**: Verifica el servicio de equipos.
  * Mock del repositorio de equipos.
  * Verifica la búsqueda de un equipo por id (nulo y no nulo).
  * Verifica la búsqueda de un equipo por nombre (lista vacía y con elementos).
  * Verifica la creación de un equipo (correcto, con contenido nulo y con nombre repetido).
  * Verifica la actualización de un equipo (correcto, con id nulo y con nombre repetido).
  * Verifica la eliminación de un equipo (correcto y con id nulo).