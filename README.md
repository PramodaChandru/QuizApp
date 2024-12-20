# Quiz Management System
## Overview
This is a Spring Boot microservices application that provides functionalities to manage questions and quizzes. The application uses MySQL as the database. Below are the key features:

## Features
1. Questions Management

 - Add a question.
 - Get all questions.
 - Get questions by category.
 - Delete a question.

2. Quiz Management

 - Create a quiz using the existing questions.
 - Retrieve a quiz by ID.
 - Submit answers for a quiz.

## Technologies Used
 - Java (Spring Boot)
 - MySQL (Database)
 - Gradle (Build Tool)
 - SpringDoc (API Documentation)
 - Lombok (Simplify code using annotations)

## Setup Instructions
### Pre-requisites
 - Java 17 or higher.
 - Gradle installed.
 - MySQL installed and running.

## Steps to Run the Application
1. Clone the Repository:
```bash
git clone <repository-url>
cd <project-folder>
```

2. Configure Database:

Open the `application.properties` or `application.yml` file.\
Update the database details:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/quiz_app
spring.datasource.username=<your-database-username>
spring.datasource.password=<your-database-password>
spring.jpa.hibernate.ddl-auto=update
```

3. Build the Project:

```bash
./gradlew clean build
```

4. Run the Application:

```bash
./gradlew bootRun
```
