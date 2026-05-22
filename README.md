# Sample CRUD Spring Boot Application

This is a sample Spring Boot CRUD application using SQLite for persistence and Swagger for API documentation.

## Features

- GET /api/items
- GET /api/items/{id}
- POST /api/items
- PUT /api/items/{id}
- PATCH /api/items/{id}
- DELETE /api/items/{id}

## Run

From the project root:

```bash
mvn spring-boot:run
```

## Swagger UI

Once the application is running, open:

`http://localhost:8080/swagger-ui.html`

## Database

The app uses `sample.db` in the project folder.
