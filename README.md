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
- MariaDB 10.6
- Maven

### How to run project

- It should create a database "bank_db". You can use a tool (ex. HeidiSQL) for creating. I set up url:localhost, port:3306, user:root and password:123. You should change params in application.yml or using these params. When you start API from (ex. IntelliJ), tables will create and API will start on "localhost:8080"
- Better solution for creating a database is integrating Liquibase configuration and xml file in API, but I haven't used in this API. Should I do that?

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
