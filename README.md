# Enterprise Data Lifecycle Manager

A simple, technical backend for managing customer data lifecycle in enterprises. Built with Spring Boot and PostgreSQL.

## Features
- Customer management (CRUD)
- Archiving and restoring records
- Privacy masking for sensitive data
- Audit logging of all operations
- Reporting and statistics
- Scheduled archive jobs

## Tech Stack
- Java 17+
- Spring Boot
- Maven
- PostgreSQL

## Getting Started
1. Clone the repository:
   ```sh
   git clone https://github.com/StaceyA132/Enterprise-Data-Lifecycle-Manager.git
   ```
2. Configure your PostgreSQL database in `src/main/resources/application.properties`.
3. Build and run the application:
   ```sh
   ./mvnw spring-boot:run
   ```

## Project Structure
- `src/main/java` - Source code
- `src/main/resources` - Configuration
- `src/test/java` - Tests

```
Initial Spring Boot backend scaffold with modules for customer, archive, privacy, audit, reporting, and scheduled jobs. PostgreSQL config ready.
```

---

For more details, see code comments and future documentation.