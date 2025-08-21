# Pahana Edu Bookshop – JDBC/SQL Version (Servlet/JSP, No Frameworks)

This variant uses **JDBC + MySQL** (allowed “Database” dependency) instead of serialization.
It still complies with your constraints:
- Java EE web app (Servlets + JSP)
- No frameworks (no Spring/Struts/Boot/ORM)
- Only 3rd-party deps: **MySQL Connector/J** and **JUnit**

## Setup

1. Create DB & tables:
   ```sql
   SOURCE schema.sql;
   ```
   (or paste it into MySQL). It creates DB `pahanaedu` and a default admin user.

2. Configure DB in `src/main/webapp/WEB-INF/db.properties`:
   ```properties
   jdbc.url=jdbc:mysql://localhost:3306/pahanaedu?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
   jdbc.user=root
   jdbc.password=YOUR_PASSWORD
   jdbc.driver=com.mysql.cj.jdbc.Driver
   ```

3. Build & deploy:
   ```bash
   mvn clean package
   # deploy target/pahana-edu-bookshop-jdbc.war to Tomcat 9
   ```

4. Open `http://localhost:8080/pahana-edu-bookshop-jdbc/`
   - **Username:** `admin`
   - **Password:** `admin123`

## Notes for Report

- **Architecture:** MVC-ish with Servlets (controllers), JDBC DAOs (persistence), POJOs (models), JSP (views).
- **Security:** Session auth filter; SHA-256 password hashing.
- **Validation & UX:** Basic server-side checks; user-friendly messages on redirects.
- **Billing:** Stores bills + bill_items with unit price snapshot for auditing.
- **SQL:** Uses `ON DUPLICATE KEY UPDATE` for upserts; transactions for bill creation.

Switching to another DB (e.g., PostgreSQL) only requires driver + SQL tweaks in DAOs.
