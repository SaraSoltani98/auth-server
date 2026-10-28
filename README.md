# Auth Server

Authentication server for the project.

## Features

- JWT authentication
- Login endpoint
- Role-based authorization
- GitHub Actions CI workflow
- Branch protection with pull requests

## Technologies

- Java
- Spring Boot
- Maven
- JWT
- GitHub Actions

## Run project

Clone repository:

git clone https://github.com/SaraSoltani98/auth-server.git

Run:

./mvnw spring-boot:run

## API endpoints

POST /auth/login

Returns JWT token after successful login.
Implementation

This service was created as the authentication service for the webshop microservice architecture.

The service handles:

User authentication

Password verification

JWT generation

Role-based authorization with USER and ADMIN roles

JWKS endpoint for public key distribution

Local development using H2

PostgreSQL database in production

The authentication service is used by the gateway, which forwards login requests and handles the authentication cookie
for the frontend.

## Contributors

Sara Soltani
