# Nursery Management System

A comprehensive RESTful backend application built with Spring Boot and PostgreSQL for managing nursery operations.

## Features

This application provides REST APIs to manage various entities within a nursery environment:
- **Users & Profiles**: System access and general user information.
- **Roles**: Administrators, Teachers, and Students management.
- **Academics**: Courses and Marks (grading) tracking.
- **Geography**: Locations and Provinces management.

## Tech Stack

- **Java**: 17
- **Framework**: Spring Boot (Web, Data JPA)
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Other**: Lombok (for boilerplate code reduction)

## Prerequisites

Before running the application, ensure you have the following installed:
- Java 17 Development Kit (JDK)
- Maven
- PostgreSQL Database server

## Database Configuration

The application uses PostgreSQL. Ensure your database server is running and configure the connection in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nursery
spring.datasource.username=postgres
spring.datasource.password=1234
```
*Note: Make sure a database named `nursery` exists, or adjust the URL accordingly. The tables will be automatically updated/created based on the entities (`spring.jpa.hibernate.ddl-auto=update`).*

## Running the Application

There are multiple ways to run the application:

### Using Maven wrapper (recommended)
Navigate to the root directory of the project and run:
```bash
# On Linux/macOS
./mvnw spring-boot:run

# On Windows
mvnw.cmd spring-boot:run
```

### Building and running the JAR file
You can package the application into a compiled JAR file and run it:
```bash
# Build the project
./mvnw clean package

# Run the compiled jar
java -jar target/nursery-management-0.0.1-SNAPSHOT.jar
```

## API Structure

The API encompasses standard CRUD operations for the following endpoints:
- `/api/users`
- `/api/profiles`
- `/api/administrators`
- `/api/teachers`
- `/api/students`
- `/api/courses`
- `/api/marks`
- `/api/locations`
- `/api/provinces`

*(Exact endpoint paths map to their respective controllers in the `controller` package.)*

## Project Structure

- `model/`: Contains JPA entities representing database tables.
- `repository/`: Contains Spring Data JPA interfaces for database operations.
- `service/`: Contains business logic and service layer abstractions.
- `controller/`: Contains REST controllers defining API endpoints.
