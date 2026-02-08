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
    Compile the project
    ```powershell
    javac -cp "lib/mysql-connector-j-9.5.0.jar" -d bin src/app/Main.java src/dao/*.java src/db/*.java src/Library/lends/*.java src/Library/resources/*.java src/Library/services/*.java             src/Library/services/strategy/*.java src/Library/services/scheduler/*.java src/Library/Users/*.java
    ```
    Run the application
    ```powershell
    java -cp "bin;lib/mysql-connector-j-9.5.0.jar" app.Main
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
    %% Actores
    Reader((Reader))
    Librarian((Librarian))
    DB[(Database System)]

    subgraph Library_System [Library Management System]
        %% Casos de Uso de Búsqueda
        UC1(Search Resources)
        UC1a(Search by Author)
        UC1b(Search by Name)
        
        %% Casos de Uso de Gestión
        UC2(Manage Loans)
        UC2a(Check Availability)
        UC2b(Update Database)
        
        %% Casos de Uso de Usuario
        UC3(View Personal History)
        UC4(Manage Catalog)
        
        %% Casos de Uso de Sistema
        UC5(Send Notifications)
    end

    %% Conexiones del Reader
    Reader --- UC1
    Reader --- UC3
    Reader --- UC5

    %% Conexiones del Librarian
    Librarian --- UC1
    Librarian --- UC2
    Librarian --- UC4
    Librarian --- UC5

    %% Relaciones Internas (Herencia de Búsqueda)
    UC1a -.->|variant| UC1
    UC1b -.->|variant| UC1

    %% Relaciones de Inclusión (Para prestar hay que mirar si está libre)
    UC2 -.->|include| UC2a
    UC2 -.->|include| UC2b
    
    %% Conexión con el Sistema Externo
    UC2b --- DB
    UC4 --- DB
````
---

```mermaid
classDiagram
    %% --- PAQUETE: DB Y DAO ---
    class DBConection {
        -String USER$
        -String PASS$
        +getConnection() Connection$
    }
    class LendDAO {
        -List<User> users
        -List<Resource> resources
        +addLend(Lend)
        +markAsReturned(Lend)
        +getAllLends() List<Lend>
    }
    class UserDAO{
        +saveUser(User)
        +getAllUsers() List<User>
    }
    class ResourceDAO{
        +saveResource(Resource)
        +getAllResources() List<Resource>
    }

    %% --- PAQUETE: USERS ---
    class User {
        <<abstract>>
        #String id
        #String name
        #ContactData data
        #UserHistory history
        +showType()*
    }
    
    class ContactData {
        -String email
        -String telefono
        +ContactData(String, String)
        +getEmail() String
    }
    
    class Librarian {
        -String turn
        +showType()
    }
    class Reader {
        -int limitLoans
        +showType()
    }

    %% --- PAQUETE: RESOURCES ---
    class Resource {
        <<abstract>>
        #String id
        #String title
        #String author
        #boolean free
        +showDetails()*
        +isFree() boolean
    }
    class Book { +showDetails() }
    class Magazine { +showDetails() }

    %% --- PAQUETE: LENDS ---
    class Lend {
        -int id
        -Resource resource
        -User user
        -LocalDate startdate
        -LocalDate finishDate
        -boolean returned
    }
    class UserHistory {
        -LinkedList<Lend> activeLends
        -LinkedList<Lend> finishedLends
        +addLend(Lend l)
        +showHistory()
    }

    %% --- PAQUETE: SERVICES & STRATEGY ---
    class LibraryManager {
        -LibraryManager instance
        -Map<String, Resource> cataloge
        -List<User> users
        +getinstance()$ LibraryManager
        +addResource(Resource)
        +addUser(User)
        +search(SearchStrategy, String query)
    }
    class FactoryResource {
        +createResource(String, String, String, String) Resource
    }
    class SearchStrategy {
        <<interface>>
        +search(Collection, String)
    }
    class AuthorSearch { +search() }
    class NameSearch { +search() }
    
    class Notifier { +sendEmail() }
    class ThreadsNotifier { +run() }

    class Main { +main(String[] args)$ }

    %% --- HERENCIA ---
    User <|-- Librarian
    User <|-- Reader
    Resource <|-- Book
    Resource <|-- Magazine
    SearchStrategy <|.. AuthorSearch
    SearchStrategy <|.. NameSearch

    %% --- CONEXIONES DE LOS DAOs (Lo que pediste) ---
    %% Todos los DAOs usan la misma conexión
    LendDAO --> DBConection : uses
    UserDAO --> DBConection : uses
    ResourceDAO --> DBConection : uses

    %% El Manager utiliza los DAOs para persistir
    LibraryManager --> LendDAO : persists lends
    LibraryManager --> UserDAO : persists users
    LibraryManager --> ResourceDAO : persists resources

    %% Los DAOs manejan las entidades correspondientes
    UserDAO ..> User : manages
    ResourceDAO ..> Resource : manages

    %% --- OTRAS RELACIONES ---
    Main --> LibraryManager : initializes
    LibraryManager o-- Resource : inventory
    LibraryManager o-- User : members
    LibraryManager --> SearchStrategy : uses
    LibraryManager --> FactoryResource : requests creation
    
    Lend "*" --> "1" User : associated to
    Lend "*" --> "1" Resource : involves
    UserHistory "1" o-- "*" Lend : aggregates
    User *-- ContactData : nested
    User --> UserHistory : manages
    ThreadsNotifier --> Notifier : uses
````
---

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
---

## E/R Diagram
```mermaid
erDiagram
    USER ||--o{ LEND : "hace"
    RESOURCE ||--o{ LEND : "es prestado"
    
    USER {
        string id PK
        string name
        string email
        string type "Reader/Librarian"
    }

    RESOURCE {
        string id PK
        string title
        string author
        string type "Book/Magazine"
        boolean free
    }

    LEND {
        int id PK
        string user_id FK
        string resource_id FK
        date start_date
        date finish_date
        boolean returned
    }
    ```