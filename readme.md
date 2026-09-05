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
- Create, view, update and delete todos
- Users can access only their own todos

### Search, Filtering & Pagination
- Keyword-based todo search
- Filter by completion status
- Sorting
- Pagination
- Configurable page size

### Admin Features
- View all registered users
- Delete users
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

## ⚙️ Configuration

The application uses environment variables for database and JWT configuration.

DB_URL
DB_USERNAME
DB_PASSWORD
JWT_SECRET
JWT_EXPIRATION

## ▶️ Run the Application

On Windows:

.\mvnw.cmd spring-boot:run

The API runs on:

http://localhost:8080

## 📚 API Documentation

Swagger UI:

http://localhost:8080/swagger-ui/index.html

## 🔐 Main API Endpoints

### Authentication

POST /users/register
POST /users/login
GET  /users/me

### Todos

GET    /todos
GET    /todos/{id}
POST   /todos/new
PUT    /todos/update/{id}
DELETE /todos/delete/{id}

### Admin

GET    /admin/users
DELETE /admin/users/{id}
GET    /admin/todos
DELETE /admin/todos/{id}
GET    /admin/dashboard

## 🧪 Build

.\mvnw.cmd clean package -DskipTests

## 🖥️ Frontend

React frontend:

https://github.com/shafi3m/todo-management-frontend

## 👨‍💻 Author

Mohammed Shafi