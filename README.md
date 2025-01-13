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
* POST /auth/login: recibe un usuario y contraseña y devuelve un token JWT.