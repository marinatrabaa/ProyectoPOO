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