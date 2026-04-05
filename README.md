{\rtf1\ansi\ansicpg1252\cocoartf2822
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\paperw11900\paperh16840\margl1440\margr1440\vieww11520\viewh8400\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 # Financial Dashboard API\
\
A backend REST API for managing users and financial transactions.\
Built with **Java 21**, **Spring Boot 3**, **PostgreSQL**, and documented via **Swagger UI**.\
\
---\
\
## Tech Stack\
- Java 21 + Spring Boot 3\
- PostgreSQL + Spring Data JPA\
- Spring Security\
- Springdoc OpenAPI (Swagger UI)\
\
---\
\
## Run Locally\
\
1. Create a PostgreSQL database named `financedb`\
2. Update `application.properties` with your DB credentials\
3. Run with `mvn spring-boot:run`\
4. Open Swagger at `http://localhost:8080/swagger-ui/index.html`\
\
---\
\
## What It Does\
\
**Auth** \'97 signup and login with role assignment (USER / ADMIN)\
\
**User Management** \'97 paginated user listing, delete, activate/deactivate accounts, privacy-focused ID lookup by email + name\
\
**Transactions** \'97 credit and withdraw money with automatic balance updates, view history, filter by type or date range\
\
**Security** \'97 inactive users blocked at login, admin routes protected by role\
\
---\
\
## Design Choices\
\
- Transactions are **immutable** \'97 no updates or deletes, matching real banking audit standards\
- Balance updates are **atomic** via `@Transactional` \'97 balance and transaction always stay in sync\
- Passwords are plain text for development \'97 BCrypt is the planned next step\
- JWT authentication is structured and ready \'97 role enforcement activates once JWT is added\
\
---\
\
## Endpoints\
\
| Area | Endpoints |\
|---|---|\
| Auth | `POST /api/auth/signup` `POST /api/auth/login` |\
| Admin | `GET /api/admin/users` `GET /api/admin/dashboard` `DELETE /api/admin/users/\{id\}` `PUT /api/admin/users/\{id\}/deactivate` |\
| Transactions | `POST /api/transactions` `GET /api/transactions/user/\{id\}` `GET /api/transactions/user/\{id\}/filter` `GET /api/transactions/user/\{id\}/date` |}