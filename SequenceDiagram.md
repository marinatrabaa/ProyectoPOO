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