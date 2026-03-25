# 📚 Study Tracker

A full-stack application for managing study sessions and tasks. Built with a Spring Boot REST API backend and a React + Vite frontend.

---

## 🚀 Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Java 17 |
| Framework | Spring Boot 4.0.2 |
| Security | Spring Security |
| Database | PostgreSQL |
| ORM | Spring Data JPA / Hibernate |
| Validation | Jakarta Bean Validation |
| Build Tool | Maven |
| Frontend | React 19 + Vite |

---

## 📐 Architecture & UML Diagram

This project follows a layered architecture: **Controller → Service → Repository → Entity**

![UML Diagram](docs/uml-diagram.png)

> *Class diagram showing Controllers, Services, Repositories, Models, Security config, and Exception handling*

---

## 🗂️ Project Structure

```
StudyTrackerBackend/demo/
└── src/main/java/com/example/demo/
   ├── controller/    # REST endpoints (TaskController, UserController, SessionController)
   ├── service/       # Business logic
   ├── repository/    # JPA interfaces for DB access
   ├── model/         # JPA entities (Task, User, StudySession) + Status enum
   ├── dto/           # Request/Response objects
   ├── exception/     # GlobalExceptionHandler, NotFoundException, ErrorResponse
   └── config/        # SecurityConfig

StudyTrackerFrontend/study-tracker-frontend/
├── src/              # React app source
├── public/
└── package.json
```

---

## 📋 Features

### Backend
- **Task Management** — Full CRUD with status tracking (`NOT_STARTED`, `IN_PROGRESS`, `COMPLETED`)
- **Study Sessions** — Create, read, update, and delete user-scoped sessions with subject filtering
- **User Management** — Register, fetch by ID, update, delete, and fetch currently authenticated user (`/users/me`)
- **Filtering** — Query tasks and sessions by `subject` and `status`
- **Authentication** — Spring Security with form login, session-based auth, and `CustomUserDetailsService`
- **Authorization** — `POST /users` is public; all other endpoints require authentication
- **Exception Handling** — Global exception handler returning structured `ErrorResponse` objects with status, message, timestamp, and request path
- **Validation** — Input validation using Jakarta constraints (`@NotBlank`, `@NotNull`, `@Size`, `@Email`, `@AssertTrue`)

### Frontend *(in progress)*
- React + Vite client scaffolded and integrated with routing
- Task/session pages and forms under active development

---

## 🔌 API Endpoints

### Tasks
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/tasks` | Create a new task |
| `GET` | `/tasks` | Get all tasks (filter by `subject`, `status`) |
| `GET` | `/tasks/{id}` | Get a task by ID |
| `PATCH` | `/tasks/{id}` | Update a task |
| `DELETE` | `/tasks/{id}` | Delete a task by ID |
| `DELETE` | `/tasks` | Delete all tasks for current user |

### Users
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/users` | Register a new user |
| `GET` | `/users/{id}` | Get a user by ID |
| `PATCH` | `/users/{id}` | Update a user |
| `DELETE` | `/users/{id}` | Delete a user |
| `GET` | `/users/me` | Get the currently authenticated user |

### Study Sessions
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/session` | Create a new session |
| `GET` | `/session` | Get sessions (filter by `subject`) |
| `GET` | `/session/{id}` | Get a session by ID |
| `PATCH` | `/session/{id}` | Update a session |
| `DELETE` | `/session/{id}` | Delete a session |
| `DELETE` | `/session` | Delete all sessions for current user |

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/login` | Authenticate user (form login) |
| `POST` | `/logout` | Log out current session |

---

## ⚙️ Getting Started

### Prerequisites
- Java 17+
- Maven
- Node.js 18+
- PostgreSQL

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/study-tracker.git
   cd study-tracker
   ```

2. **Configure your database** in `StudyTrackerBackend/demo/src/main/resources/application.properties`
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/studytracker
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

4. **Run the backend**
   ```bash
   cd StudyTrackerBackend/demo
   mvn spring-boot:run
   ```

5. **Run the frontend**
   ```bash
   cd StudyTrackerFrontend/study-tracker-frontend
   npm install
   npm run dev
   ```

6. **Open the frontend** at `http://localhost:5173`

---

## 📝 Comments & JavaDoc

The backend uses JavaDoc-style comments for classes and methods to keep the codebase readable and resume-ready.

### Comment Style Used In This Project
- Class-level JavaDocs describe each layer's responsibility (Controller, Service, Repository, Model, DTO, Exception, Config)
- Method JavaDocs explain parameters, return values, and behavior
- Inline comments are used sparingly for non-obvious logic only

### Generate JavaDocs

Run this from the backend project root:

```bash
cd StudyTrackerBackend/demo
./mvnw.cmd javadoc:javadoc
```

### Open Generated JavaDocs

After generation, open:

`StudyTrackerBackend/demo/target/reports/apidocs/index.html`

### Optional: Clean + Rebuild JavaDocs

```bash
cd StudyTrackerBackend/demo
./mvnw.cmd clean javadoc:javadoc
```

---

## 🧠 What I Learned / Key Concepts

- Designing a layered REST API with clear separation of concerns
- Spring Security with a custom `UserDetailsService` and `UserDetails` implementation
- Global exception handling with structured error responses
- DTO pattern to decouple API contracts from internal models
- Custom `@Query` annotations in Spring Data JPA
- User-scoped repository queries for secure data access
- Connecting a React + Vite frontend to a Spring Boot backend


---

## 🔮 Planned Improvements

- [ ] Complete dashboard UI (functional)
- [ ] Style and polish frontend
- [ ] Unit and integration tests
- [ ] Swagger / OpenAPI documentation

---

## 📄 License

No license file is currently included in this repository.
