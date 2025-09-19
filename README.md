
# SimpleBlog project

This is the demo blog backend application, built using Spring Boot. It provides API for managing blog. 

## Version
| Version | Date       |                                          |                                                                        
|---------|------------|------------------------------------------|
| 0.1     | 2025/09/17 | Demo version to test different framework |   
| 0.2     | 2025/09/19 | support mongodb replica sets             |  


## Features

### Comment Management:
- Retrieve, save, publish, and delete blog comments.

## Technologies Used
- **Spring Boot**: Framework for building the backend application.
- **MongoDB**: A document-oriented NoSQL database.


## Installation

### Prerequisites
- **Java 17 or higher**

### Run the Application:

The application will start on `http://localhost:8887`.


## API Endpoints
#### api prefix /admin is configured for all controllers

### Comment Management
- **GET /comments/{id}**: Retrieve details of a comment by id.
- **GET /comments/all**: Retrieve all comments (no pagination yet).
- **POST /comments/create**: create and save a new comment.
- **POST /comments/{id}/publish**: publish a comment.
- **DELETE /comments/{id}**: Delete a comment.
- **POST /comments/{id}/like**: like a comment.
- **POST /comments/{id}/unlike**: unlike a comment.


## Exception Handling
The application uses global exception handling to handle specific scenarios
