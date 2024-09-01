# Ecommerce Spring Boot Application

An Ecommerce application built using Spring Boot that provides functionalities for user registration, login, password reset, product listing, and cart management.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [License](#license)

## Introduction

This project is a basic Ecommerce application designed to demonstrate a simple web application's functionality using the Spring Boot framework. The application includes essential ecommerce features like user registration, login, password reset, and basic product management.

## Features

- **User Management**: Register, login, password reset functionalities.
- **Email Integration**: Sends emails for password reset using JavaMailSender.
- **Security**: Password encryption using SHA-256.

## Technologies Used

- **Java 17**
- **Spring Boot 3.3.3**
- **Spring Data JPA**
- **Spring Security**
- **MySQL Database**
- **Thymeleaf** - For frontend templating
- **JavaMailSender** - For sending emails
- **Maven** - Build tool

## Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/cloudysman/ecommerce-springboot.git
   cd ecommerce-springboot
2. **Configure Database**'
   Update the src/main/resources/application.properties file with your own MySQL database credentials:
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
   spring.datasource.username=root
   spring.datasource.password=yourpassword
3. **Install Maven Dependencies**
   Make sure Maven is installed and run the following command to download all dependencies:
   ```bash
   mvn clean install
4. **Run the Application**
   Use the following command to start the application:
   ```bash
   mvn spring-boot:run
Alternatively, you can run the application using your IDE (e.g., Eclipse, IntelliJ IDEA).

## Usage
1. **Access the Application**
   Open your web browser and navigate to http://localhost:8080.
2. **Register a New User**
   Click on the "Register" link and fill out the form to create a new user account.
3. **Login**
   Use the registered credentials to log in to the application.
4. **Reset Password**
   Use the "Forgot Password" functionality to receive a password reset link via email.
## Endpoints
The application exposes the following REST endpoints:

- POST /api/forgotpassword - To request a password reset link.
- POST /api/resetpassword - To reset the password using the token sent via email.
