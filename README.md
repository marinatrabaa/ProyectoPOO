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
5. Execution via Terminal:
   ```
  Compile the project
  ```powershell
  javac -cp ".;lib/mysql-connector-j-8.0.33.jar" -d bin src/app/Main.java
  ```
  Run the application
  ```powershell
  java -cp "bin;lib/mysql-connector-j-8.0.33.jar" app.Main
  ```
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

## Use Case Diagram
```mermaid
graph LR
    subgraph "Library System"
        UC1(Manage Resources)
        UC2(Lend Resource)
        UC3(Return Resource)
        UC4(Register User)
        UC5(Send Notifications)
    end

    Librarian((Librarian)) --- UC1
    Librarian --- UC2
    Librarian --- UC3
    Librarian --- UC4

    Reader((Reader)) --- UC2
    Reader --- UC3

    UC2 --- DB[(Database)]
    UC3 --- DB
    UC5 --- DB
````

## Class Diagram
````mermaid
classDiagram
    class Resource {
        <<abstract>>
        -String id
        -String title
        -String author
        +getId() String
        +getTitle() String
        +getAuthor() String
    }

    class Book {
        -String isbn
        +getIsbn() String
    }

    class Magazine {
        -int number
        +getNumber() int
    }

    class User {
        -String id
        -String name
        -String email
        +getId() String
        +getName() String
    }

    class Lend {
        -int id
        -Resource resource
        -User user
        -LocalDate startDate
        -LocalDate finishDate
        -boolean returned
        +isReturned() boolean
        +setReturned(boolean)
        +getResource() Resource
        +getUser() User
    }

    class LendDAO {
        -List~User~ users
        -List~Resource~ resources
        +LendDAO(List~User~, List~Resource~)
        +addLend(Lend lend) void
        +markAsReturned(Lend lend) void
        +getAllLends() List~Lend~
    }

    class DBConection {
        +getConnection() Connection$
    }

    class ReminderThread {
        -LendDAO lendDAO
        +ReminderThread(LendDAO)
        +run() void
        -checkExpirations() void
        -sendEmail(User) void
    }

    class Main {
        +main(String[] args)$
        -initializeData() void
    }

    Resource <|-- Book
    Resource <|-- Magazine
    Lend "1" --> "1" User
    Lend "1" --> "1" Resource
    LendDAO ..> Lend : manipulates
    LendDAO ..> DBConection : uses
    ReminderThread --> LendDAO : queries
    Main ..> Librarian : interacts
    Main ..> LendDAO : invokes
````
## Sequence Diagram
```mermaid
sequenceDiagram
    autonumber
    participant M as Main
    participant L as Librarian
    participant DAO as LendDAO
    participant DB as MySQL Database
    participant T as ReminderThread

    Note over M, DB: --- LENDING PROCESS ---
    
    M->>L: manageLend(user, resource)
    Note over L: Verify availability
    L->>DAO: addLend(newLend)
    
    activate DAO
    DAO->>DB: getConnection()
    DB-->>DAO: Connection Object
    
    DAO->>DB: INSERT INTO lends (...)
    activate DB
    DB-->>DAO: rowsAffected, generatedID
    deactivate DB
    
    Note over DAO: lend.setId(generatedID)
    DAO-->>L: Success
    deactivate DAO
    
    L-->>M: Lend Confirmed
    
    M->>T: start()
    activate T
    Note right of T: Parallel email sending
    T->>T: sendEmail(user)
    deactivate T

    Note over M, DB: --- RETURN PROCESS ---

    M->>L: returnResource(lend)
    L->>DAO: markAsReturned(lend)
    
    activate DAO
    DAO->>DB: UPDATE lends SET returned = true WHERE id = ?
    activate DB
    DB-->>DAO: rowsUpdated
    deactivate DB
    
    Note over DAO: lend.setReturned(true)
    DAO-->>L: Return Confirmed
    deactivate DAO
    L-->>M: Success Message
````
