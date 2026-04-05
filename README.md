# Gamified Habit Tracker

##  Setup Instructions

### 1️ Clone the Project
```bash
git clone <repo-url>
cd Mini Project
```

### 2️ Configure Database

Create the database in PostgreSQL:
```sql
CREATE DATABASE gamifiedHabitTracker;
```

Update `application.properties` if needed:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gamifiedHabitTracker
spring.datasource.username=group1
spring.datasource.password=123
```

### 3️ Setup Environment Variables

In IntelliJ → **Run** → **Edit Configurations** → **Environment Variables**

Add the following:
MAIL_PASSWORD=gmail_app_password
S3_URI=http://localhost:9000
S3_ACCESS_KEY=admin
S3_SECRET_KEY=password
S3_BUCKET=habit-bucket

### 4️ Start Docker Services

Make sure you're inside the project folder, then run:
```bash
docker compose up -d
```

This will start:

- **Redis** → `localhost:6379`
- **RustFS** → `localhost:9000` (API), `localhost:9001` (UI)

### 5️ Run the Application
```bash
mvn spring-boot:run
```

### 6️ Access API

**Swagger UI:**
http://localhost:8080/swagger-ui/index.html
