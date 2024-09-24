Documentation for News Approval and Deletion Process


Table of Contents

Introduction

Setup Instructions

Prerequisites

Installation

Configuration

API Endpoints

Authentication APIs

User Management APIs

News Management APIs

Maintenance

Further Development

Additional Resources

1. Introduction
   
This documentation provides an overview of the News Approval and Deletion Process implemented using Spring Boot . It details the setup instructions, available API endpoints, and guidelines for maintenance and further development.

2. Setup Instructions
    Prerequisites ,
   Java 11 ,
   Maven 3.6 or higher,
   A MySQL database (or other supported databases).
   Installation
  Clone the repository:

   git clone  https://github.com/ayafathy/news.git.
   cd news
   Build the project:

    mvn clean install
    Run the application:
    mvn spring-boot:run
    Access the application: The application will be available at http://localhost:8080.

  Configuration
  Database Configuration:
  Update application.properties  with your database configurations.
   properties

  spring.datasource.url=jdbc:mysql://localhost:3306/news_schema
  spring.datasource.username=root
  spring.datasource.password=your_password

 3. API Endpoints
    
Authentication APIs
Login

Endpoint: POST /api/auth/login
Request Body:


{
  "email": "Admin@test.com",
  "password": "p@ssw0rd"
}
Response:
200 OK: Returns a JWT token.
401 Unauthorized: Invalid credentials.

Signup

Endpoint: POST /api/auth/signup
Request Body:

{
  "fullName": "John Doe",
  "email": "user@example.com",
  "password": "your_password",
  "dateOfBirth": "YYYY-MM-DD"
}
Response:
201 Created: User created successfully.
400 Bad Request: Validation errors.
Logout


Endpoint: POST /api/auth/logout
Response:
200 OK: Successfully logged out.

User Management APIs
Create User

Endpoint: POST /api/users
Response: 201 Created: User created.
Get User

Endpoint: GET /api/users/{id}
Response: 200 OK: User details.
Update User

Endpoint: PUT /api/users/{id}
Response: 200 OK: User updated.
Delete User

Endpoint: DELETE /api/users/{id}
Response: 204 No Content: User deleted.
News Management APIs
Create News

Endpoint: POST /api/news

Request paramter 
  "title": "News Title",
  "titleAr": "عنوان الأخبار",
  "description": "News Description",
  "descriptionAr": "وصف الأخبار",
  "publishDate": "YYYY-MM-DD",
  "image":

Response: 200 Created: News item created with pending status.
Get News

Endpoint: GET /api/news
Response: 200 OK: List of news items.
Update News

Endpoint: PUT /api/news/update/{id}
Response: 200 OK: News item updated.
Delete News

Endpoint: DELETE /api/news/delete/{id}
Response: 200 No Content: News item deleted (if pending).

4. Maintenance
   
Database Maintenance: Regularly back up the database and monitor its performance.
API Documentation: Use tools like Swagger for auto-generating API documentation.
http://localhost:8080/swagger-ui/

6. Further Development

Feature Enhancements: Implement the workflow using Camunda 

Unit Testing: Write unit and integration tests using JUnit and Mockito for better reliability.

7. Additional Resources

Spring Boot Documentation: Spring Boot Documentation
Postman for API Testing: Postman
