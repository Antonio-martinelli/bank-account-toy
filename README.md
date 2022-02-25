# Bank Account Toy

A small application with REST API based on the following scenario.

## Prerequisites:
We have an existing bank customer with money in the following accounts:
- Checking account (functions as a reference account for the savings account)
- Savings account
- Private loan account

Every account has an IBAN assigned and will be referenced by this.
All accounts are having following capabilities:
- Checking account - transferring money from and to any account is possible
- Savings account - transferring money from any account is possible. Only transferring money from the savings account to the reference account (checking account) is possible.
- Private loan account - transferring money from any account is possible. Withdrawal is not
possible

## Use cases
- Deposit money into a specified bank account
- Transfer some money across two bank accounts
- Show current balance of the specific bank account
- Filter accounts by account type
- Show a transaction history
- Account locking

## Tests
Tests have been omitted and resolved using a strong exception handling, considering time constraints and awaneress of the use cases to be implemented. Test of the API has been executed using Postman. In LoadDatabase.java a small set of accounts is set to initialize the scenario.

## Endpoints

- PUT "/deposit" : for the deposit (Use case 1)
    
    Body constraints: 
    ```
    {"iban":"DE34857274830271880419",
    "amount":400}
    ```
- POST "/transactions" : to implement the transfer (Use case 2)
    
    Body constraints: 
    ```
    {"sender":"DE34857274830271880419",
    "receiver":"DE34857243850271880415",
    "amount":400}
    ```
- POST "/accounts/balance" : to retrieve balance of an account (Use case 3)
    
    Body constraints: 
    ```
    {"iban": "DE34857274830271880416"}
    ```
- GET "/accountsByType/{type} : to filter accounts by account type (Use case 4)

- POST "accounts/history" : to get the transaction history (Use case 5)
    
    Body constraints: 
    ```
    {"iban": "DE34857274830271880416"}
    ```
- POST "/lockAccount": to lock/unlock an account (Use case 6)
    
    Body constraints: 
    ```
    {"iban": "DE34857274830271880419", 
    "lock": true}
    ```

## Startup
Clone the repository.

Simply run the project through an IDE or from the terminal: ```./mvnw spring-boot:run```

## Notes
This project misses metrics/loggings tracing and proper configuration in order to be effectively executed in a production environment.