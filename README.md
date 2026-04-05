# Financial Dashboard API

A backend REST API for managing users and financial transactions.
Built with **Java 21**, **Spring Boot 3**, **PostgreSQL**, and documented via **Swagger UI**.

---

## Tech Stack
- Java 21 + Spring Boot 3
- PostgreSQL + Spring Data JPA
- Spring Security
- Springdoc OpenAPI (Swagger UI)

---

## Run Locally

1. Create a PostgreSQL database named `financedb`
2. Update `application.properties` with your DB credentials
3. Run with `mvn spring-boot:run`
4. Open Swagger at `http://localhost:8080/swagger-ui/index.html`

---

## What It Does

**Auth** — signup and login with role assignment (USER / ADMIN)

**User Management** — paginated user listing, delete, activate/deactivate accounts, privacy-focused ID lookup by email + name

**Transactions** — credit and withdraw money with automatic balance updates, view history, filter by type or date range

**Security** — inactive users blocked at login, admin routes protected by role

---

## Design Choices

- Transactions are **immutable** — no updates or deletes, matching real banking audit standards
- Balance updates are **atomic** via `@Transactional` — balance and transaction always stay in sync
- Passwords are plain text for development — BCrypt is the planned next step
- JWT authentication is structured and ready — role enforcement activates once JWT is added

---

## Endpoints

| Area | Endpoints |
|---|---|
| Auth | `POST /api/auth/signup` `POST /api/auth/login` |
| Admin | `GET /api/admin/users` `GET /api/admin/dashboard` `DELETE /api/admin/users/{id}` `PUT /api/admin/users/{id}/deactivate` |
| Transactions | `POST /api/transactions` `GET /api/transactions/user/{id}` `GET /api/transactions/user/{id}/filter` `GET /api/transactions/user/{id}/date` |
