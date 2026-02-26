# GrowthHub Backend

Spring Boot API for **GrowthHub** – personal productivity & competitive growth tracker for college students.

## Stack

- **Java 17**, **Maven**
- **Spring Web**, **Spring Data JPA**, **Spring Security**, **OAuth2 Client**
- **PostgreSQL** (with UUID support), **Lombok**

## Architecture

- **Layered**: `controller` → `service` → `repository`; `entity`, `dto`, `config`, `security`, `exception`.
- **Dashboard vs Detail API separation**
  - **Dashboard**: returns **only summary data** (counts, aggregates) so the main dashboard stays fast.
  - **Detail**: dedicated endpoints for each area when the user opens a card (DSA, LeetCode, GitHub, Productivity).
- **No entities exposed**: all responses go through DTOs (summary DTOs for dashboard, detailed DTOs for detail pages).

## Auth Flow

1. **Landing** → user clicks Login.
2. **OAuth**: redirect to `/api/oauth2/authorization/google` or `/api/oauth2/authorization/github`.
3. **Callback**: Spring Security handles callback; `GrowthHubOAuth2UserService` loads or creates user (by `authProvider` + `oauthSubject`).
4. **Session**: user is logged in; frontend can call **GET /auth/me** for summary user info.
5. **Logout**: **POST /auth/logout** (and clear frontend session).

**Public routes**: `/auth/**`, `/health`  
**Secured**: all other endpoints (require authenticated user).

## Database Configuration

Uses **environment variables** (see `application.yml`):

| Variable | Description | Default |
|----------|-------------|---------|
| `DATABASE_URL` | JDBC URL | `jdbc:postgresql://localhost:5432/growthhub` |
| `DATABASE_USERNAME` | DB user | `postgres` |
| `DATABASE_PASSWORD` | DB password | `postgres` |
| `DATABASE_POOL_SIZE` | Hikari max pool size | `10` |
| `JPA_DDL_AUTO` | Hibernate ddl-auto | `validate` |
| `GITHUB_CLIENT_ID` / `GITHUB_CLIENT_SECRET` | GitHub OAuth | required |
| `GOOGLE_CLIENT_ID` / `GOOGLE_CLIENT_SECRET` | Google OAuth | required |
| `SERVER_PORT` | Server port | `8080` |

- **JPA/Hibernate**: dialect `PostgreSQLDialect`; UUID support via `preferred_uuid_jdbc_type: OTHER`.
- **Dev profile** (`spring.profiles.active=dev`): `ddl-auto: update`, SQL logging.

## DB Schema (main entities)

- **users**: `id` (UUID), `name`, `email`, `auth_provider`, `oauth_subject`, `github_username`, `leetcode_username`, `role`, `leaderboard_opt_in`, `created_at`, `updated_at`
- **dsa_progress**: per-user; `total_questions`, `solved_questions`, `last_updated`
- **leetcode_stats**: per-user; easy/medium/hard/total solved, streak
- **github_activity**: per-user; public_repos, contribution_count
- **daily_tasks**: user, task_date, title, completed, sort_order
- **daily_efficiency**: user, efficiency_date, score, goals_completed, goals_total
- **productivity_insights**: precomputed; scope (USER/GLOBAL), user_id (optional), insights_json
- **leaderboard**: no separate table; uses `users.leaderboard_opt_in`; rankings computed from DSA progress and daily efficiency.

## API Overview

| Area | Dashboard (summary only) | Detail / other |
|------|---------------------------|----------------|
| **Auth** | — | GET /auth/me, POST /auth/logout |
| **User** | — | GET /users/{id}, GET /users/profile |
| **DSA** | GET /dashboard/dsa-summary | GET /progress/dsa/details, POST /progress/dsa/increment, /decrement |
| **LeetCode** | GET /dashboard/leetcode-summary | GET /leetcode/stats, POST /leetcode/link |
| **GitHub** | GET /dashboard/github-summary | GET /github/details |
| **Productivity** | GET /dashboard/productivity-summary | GET /daily/tasks/{date}, GET /daily/efficiency/{date}, POST /daily/tasks, POST /daily/tasks/{id}/complete |
| **Leaderboard** | — | POST /leaderboard/opt-in, GET /leaderboard/weekly-consistency, /improvement, /total-solved |
| **Insights** | — | GET /insights/user, GET /insights/global |
| **System** | — | GET /health |

Base URL: `http://localhost:8080/api` (context path `/api`).

## Deployment

1. Create DB: `createdb growthhub` (or your DB name in `DATABASE_URL`).
2. Set env vars (DB + OAuth client ids/secrets).
3. **Production**: `JPA_DDL_AUTO=validate` (no auto DDL); run migrations or use `update` once.
4. Run: `mvn spring-boot:run` or build jar and `java -jar growthhub-backend.jar`.
5. Dev: `mvn spring-boot:run -Dspring-boot.run.profiles=dev`.
