
# ForoHub API

## Descripción

API REST para gestionar usuarios, tópicos y respuestas de un foro, desarrollada con Spring Boot.

---

## Requisitos previos

- Java 17 o superior instalado
- MySQL corriendo localmente o remoto
- Variables de entorno configuradas (ver sección Configuración)

---

## Tecnologías usadas

Este proyecto está desarrollado con:

- **Java 17**  
- **Spring Boot 3.5.3**  
- **Spring Web**: para crear la API REST  
- **Spring Security**: para manejar autenticación y autorización con JWT  
- **Spring Data JPA**: para interactuar con la base de datos MySQL  
- **Flyway**: para gestionar migraciones de base de datos  
- **MySQL Connector**: driver JDBC para MySQL  
- **Lombok**: para reducir código boilerplate  
- **JWT (Java JWT - com.auth0)**: para generación y validación de tokens  
- **Springdoc OpenAPI**: para documentación automática y Swagger UI  
- **Validación con Jakarta Validation**: para validar datos de entrada  
- **Dependencias de testing**: Spring Boot Test y Spring Security Test


## Configuración

Definir las siguientes variables de entorno:

```bash
export DATASOURCE_URL=jdbc:mysql://localhost:3306/forohub_api
export DATASOURCE_USERNAME=root
export DATASOURCE_PASSWORD=tu_password
export JWT_SECRET=tu_secreto_jwt
```

## Estructura del proyecto

- `controller/` - Controladores REST  
- `domain/` - Clases de dominio y servicios  
- `infra/` - Configuraciones, seguridad y excepciones  
- `resources/` - Configuración de Spring Boot (application.properties, etc)  

## Endpoints y seguridad

- Registro de usuario: libre (no requiere token)  
- Login: libre (no requiere token, pero sí se debe enviar email y contraseña correctos)
- Resto de endpoints: requieren token JWT válido en el header Authorization  

## Ejemplos de JSON para requests
Registro de usuario (POST /usuarios)

```
{
  "nombre": "Juan Pérez",
  "email": "juan.perez@example.com",
  "contrasena": "tuPassword123"
}
```

Login (POST /login)

```
{
  "email": "juan.perez@example.com",
  "contrasena": "tuPassword123"
}
```

## Manejo de errores

El API responde con códigos HTTP y mensajes claros en caso de errores comunes:

- 400 - Datos inválidos o faltantes  
- 401 - No autenticado o token inválido  
- 403 - Acceso denegado  
- 404 - Recurso no encontrado  
- 500 - Error interno del servidor  

## Testing

Podés probar los endpoints usando:

- [Swagger UI](http://localhost:8080/swagger-ui/index.html)  
- Postman / Insomnia (usar header Authorization: Bearer <token>)

## Mejoras futuras (opcional)

- Agregar endpoints para gestionar respuestas y comentarios  
- Soporte para roles y permisos más avanzados  
- Registro y recuperación de contraseña 

