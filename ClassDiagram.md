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
    ```