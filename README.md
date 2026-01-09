# Library Management System – Object-Oriented Programming Project

## Overview

This project is a **Library Management System** developed in **Java** as part of an **Object-Oriented Programming (OOP)** course.  
The application models the core functionalities of a library, including resource management, users, lending operations, and notifications, applying key OOP principles such as **encapsulation, inheritance, polymorphism, abstraction**, and several **design patterns**.

The system is structured following a layered architecture and simulates interactions between librarians, readers, and library resources.

---

## Features

- Management of library resources (books, magazines, etc.)
- User system with different roles:
  - Librarian
  - Reader
- Lending and return of resources
- User lending history
- Database connection layer
- Notification system for lending events
- Application of multiple OOP design patterns

---

## Technologies Used

- **Java**
- **JDBC** (for database connectivity)
- **Object-Oriented Programming principles**
- **Design Patterns**:
  - Factory
  - Strategy
  - DAO (Data Access Object)
  - Singleton
- **Multithreading** (for scheduled notifications)

---

## Project Structure

```
ProyectoPOO/
│
├── src/
│   ├── app/                # Application entry point
│   │   └── Main.java
│   │
│   ├── dao/                # Data Access Objects
│   │   └── LendDAO.java
│   │
│   ├── db/                 # Database connection
│   │   └── DBConection.java
│   │
│   ├── Library/
│   │   ├── lends/          # Lending-related classes
│   │   ├── resources/      # Library resources
│   │   ├── services/       # Business logic
│   │   └── Users/          # User hierarchy
│   │
├── lib/                    # External libraries (JARs)
├── test/                   # Test classes
└── README.md
```

---

## How to Run the Project

1. Install **Java JDK 8 or higher**.
2. Open the project in an IDE such as:
   - IntelliJ IDEA
   - Eclipse
   - NetBeans
3. Configure the database connection in:
   ```
   src/db/DBConection.java
   ```
4. Add required JAR files to the project build path.
5. Run the application from:
   ```
   src/app/Main.java
   ```

---

## Database Configuration

The system uses a database to store lending information.  
Edit the following parameters in `DBConection.java`:

- Database URL
- Username
- Password

---

## Object-Oriented Design

This project follows solid OOP principles:

- **Encapsulation**: Data and behavior are properly separated
- **Inheritance**: Resource and user hierarchies
- **Polymorphism**: Use of interfaces and abstract classes
- **Abstraction**: Service layers and DAO pattern
- **Design Patterns** to improve scalability and maintainability

---
