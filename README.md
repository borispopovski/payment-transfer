# Payment Transfer Service

A simple and secure fund transfer service built with **Spring Boot**, **Java 17**, **Spring Data JPA**, and **MariaDB**. This microservice allows users to transfer money between accounts within the same banking system.

---

## Features

- Transfer funds between two accounts
- Add account
- Validates sufficient balance in the source account
- Persists transaction records for auditing
- Comprehensive error handling
- Configurable via Spring Boot

---

## Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- MariaDB
- Maven

### Endpoints

- POST /api/account
```json
{
  "balance" : 100
}
```

- POST /api/transfer
```json
{
  "fromAccountId": "uuid",
  "toAccountId": "uuid",
  "amount": 50
}
```

### Database Setup

Create the database in MariaDB:

```sql
CREATE DATABASE bank_db;
