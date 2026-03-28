# second-round-assignm-final-12574-arjun
Final Project Assignment - This repository contains the complete final project code and documentation.


# E-Commerce Backend System

## Project Overview
This project is a backend implementation of a simple E-commerce platform built using Spring Boot. It provides REST APIs for user authentication, product management, cart operations, order processing, and payment integration.

---

## Features

# User Authentication & Authorization
- User Registration & Login
- JWT-based Authentication
- Role-based Authorization (USER / ADMIN)
- Password encryption using BCrypt

---

###  Product Management
- Create Product
- Get All Products
- Get Product by ID
- Update Product
- Delete Product

---
###  Cart Management
- Add product to cart
- Remove product from cart
- View cart items
- Quantity management

---

###  Order Management
- Create order from cart
- View user orders
- Store order details (price, address, items)

---

###  Payment Integration
- Integrated Stripe Payment Gateway
- Created PaymentIntent using Stripe API
- Payment status tracking (SUCCESS / FAILED)

---

###  Exception Handling
- Global Exception Handling using @RestControllerAdvice
- Custom exceptions:
  - ResourceNotFoundException
  - UnauthorizedException

---

##  Tech Stack

- Java 25
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- MySQL
- Stripe API
- Lombok

