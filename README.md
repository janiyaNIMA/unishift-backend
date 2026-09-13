# UniShift Backend

UniShift Backend is a Spring Boot REST API for student authentication and registration. The project currently supports user registration, login checks, and persistence with a relational database.

## Overview

This backend is designed for a student-focused application where users can:

- register with a username and password
- provide student details such as university and student ID
- log in using their username and password
- store user records in a PostgreSQL database

## Tech Stack

- Java 17
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- PostgreSQL driver
- H2 in-memory database for local/test usage
- Maven

## Project Structure

```text
unishift-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── unishift_backend/
│   │   │       ├── Main.java
│   │   │       ├── UnishiftBackendApplication.java
│   │   │       ├── controller/
│   │   │       │   └── AuthController.java
│   │   │       ├── dto/
│   │   │       │   ├── LoginRequest.java
│   │   │       │   └── RegisterRequest.java
│   │   │       ├── model/
│   │   │       │   ├── User.java
│   │   │       │   └── Student.java
│   │   │       └── repository/
│   │   │           └── UserRepository.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── unishift_backend/
│               └── AuthControllerTest.java
├── .env.example
├── pom.xml
├── mvnw
├── README.md
└── .gitignore
```

## Main Components

### AuthController
The API entry point is located in `AuthController` and exposes:

- `POST /api/auth/register`
- `POST /api/auth/login`

### Models
- `User`: base entity for application users
- `Student`: extends `User` and adds university and student ID fields

### Repository
- `UserRepository`: JPA repository with methods for user lookup and username checks

## API Endpoints

### Register a student

Request:

```http
POST /api/auth/register
Content-Type: application/json
```

Body example:

```json
{
  "username": "student_sam",
  "password": "pass123",
  "name": "Sam Perera",
  "universityName": "University of Colombo",
  "studentIdNumber": "STU-1001"
}
```

Possible responses:

- `201 Created` -> `User registered successfully!`
- `409 Conflict` -> `Username is already taken!`
- `400 Bad Request` -> `Username and password are required!`

### Login

Request:

```http
POST /api/auth/login
Content-Type: application/json
```

Body example:

```json
{
  "username": "student_sam",
  "password": "pass123"
}
```

Successful response example:

```text
Welcome back Sam Perera!
```

If credentials are invalid, the API responds with:

```text
Invalid username or password!
```

## Environment Configuration

The project reads database settings from environment variables, with defaults already configured in `application.properties`.

You can copy the sample env file:

```bash
cp .env.example .env
```

Example values:

```env
SERVER_PORT=8080
DB_URL=jdbc:postgresql://<HOST>/<DATABASE>?sslmode=require&channel_binding=require
DB_USERNAME=your_db_username
DB_PASSWORD=your_db_password
```

## Running the Project

### 1. Install dependencies

```bash
./mvnw clean install
```

### 2. Start the application

```bash
./mvnw spring-boot:run
```

The app runs on:

```text
http://localhost:8080
```

## Running Tests

```bash
./mvnw test
```

The test suite in `AuthControllerTest` covers:

- successful student registration
- duplicate username rejection
- missing credentials validation
- login flow after registration
- student model behavior

## Notes

- The application is currently configured for PostgreSQL in production-style setup.
- `spring.jpa.hibernate.ddl-auto=update` is enabled, so the schema is created or updated automatically.
- The project includes H2 and test dependencies for development and testing workflows.

## License

This project does not declare a specific license in the current configuration.
