# Todo Management API

A RESTful Todo Management API built with Spring Boot, MySQL, JWT authentication, and role-based authorization.

The application allows users to securely manage their own todos, while administrators can manage users, view all todos, delete todos, and access dashboard statistics.

## 🚀 Features

### Authentication & Authorization
- User registration
- BCrypt password hashing
- User login
- JWT-based authentication
- Protected REST endpoints
- Role-based authorization
- USER and ADMIN roles

### Todo Management
- Create a todo
- View todos
- View a todo by ID
- Update a todo
- Delete a todo
- Users can access only their own todos

### Search, Filtering & Pagination
- Keyword-based todo search
- Filter by completion status
- Sorting
- Pagination
- Configurable page size

### Admin Features
- View all registered users
- View all todos
- Delete any todo
- Dashboard statistics
    - Total users
    - Total todos
    - Completed todos
    - Pending todos

### API Documentation
- Swagger / OpenAPI documentation
- Interactive API testing
- JWT Bearer authentication support

### Additional Features
- Request validation
- Global exception handling
- Consistent API response structure
- CORS configuration
- Externalized database and JWT configuration

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 26 | Programming language |
| Spring Boot 4.1 | Backend framework |
| Spring Web MVC | REST API |
| Spring Data JPA | Database access |
| Hibernate | ORM |
| Spring Security | Authentication & authorization |
| JWT | Token-based authentication |
| BCrypt | Password hashing |
| MySQL | Database |
| Maven | Build & dependency management |
| Swagger / OpenAPI | API documentation |
| Lombok | Boilerplate reduction |

## 🏗️ Architecture

```text
Client / Postman / Swagger
            ↓
       REST Controller
            ↓
          Service
            ↓
       JPA Repository
            ↓
        Hibernate
            ↓
          MySQL

