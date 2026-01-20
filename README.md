
# 🎟️ TicketMaster Core – High-Concurrency Event Booking Engine

## 📌 Project Overview

TicketMaster Core is a **Spring Boot backend system** designed to handle **high-concurrency event ticket booking** scenarios. The system prevents **double seat booking**, supports **dynamic tier-based pricing**, and maintains **audit logs** for failed booking attempts using **Spring AOP**.

This project was developed as part of **ICET Spring Boot coursework**.

---

## 🛠️ Technologies Used

* Java 17
* Spring Boot 3.x
* Spring Web
* Spring Data JPA
* Spring AOP
* MySQL
* Lombok
* Maven
* Postman

---

## 📂 Project Structure

```
com.ticketmaster
 ├── controller     // REST controllers
 ├── service        // Business logic
 ├── repository     // JPA repositories
 ├── entity         // JPA entities
 ├── strategy       // Pricing strategies (Strategy Pattern)
 ├── aspect         // AOP audit logging
 ├── annotation     // Custom annotations
 ├── exception      // Custom exceptions
 └── dto            // DTO classes
```

---

## 🧩 Core Features

### 1️⃣ 10-Minute Seat Locking (Concurrency Handling)

* Users can **hold a seat for 10 minutes** before booking.
* Uses **Pessimistic Database Locking** to prevent double booking.
* If a seat is already held:

  * Expired hold → overwritten
  * Active hold → throws `SeatLockedException` with remaining time

**Endpoint:**

```
POST /seats/{id}/hold?userId={userId}
```

---

### 2️⃣ VIP Tiered Pricing Engine (Strategy Pattern)

Pricing depends on **user tier** and **event demand**:

| User Tier | Pricing Rule                      |
| --------- | --------------------------------- |
| REGULAR   | Base price                        |
| VIP       | 10% discount (unless high demand) |
| PLATINUM  | Base price + Priority Access      |

* Implemented using **Strategy Pattern**
* Easy to extend without changing existing logic

---

### 3️⃣ Audit Shadow (Spring AOP)

* Captures **failed booking attempts**
* Uses custom annotation `@AuditFailure`
* Logs details to `audit_logs` table:

  * User ID
  * Timestamp
  * Failure reason

---

## 🗄️ Database Schema

### Tables

* `users`
* `events`
* `seats`
* `bookings`
* `audit_logs`

The schema is automatically created using **Hibernate (ddl-auto=update)**.

---

## ⚙️ Configuration

### `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ticketmaster_db
spring.datasource.username=root
spring.datasource.password=1234

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

---

## ▶️ How to Run the Project

1. Create MySQL database:

```sql
CREATE DATABASE ticketmaster_db;
```

2. Open project in **IntelliJ / Eclipse**
3. Run `TicketMasterApplication.java`
4. Test APIs using **Postman**

---

## 🧪 Postman Testing

### Hold Seat API

```
POST http://localhost:8080/seats/1/hold?userId=1
```

* Run the same request in two tabs to test **concurrency handling**

---
* Diagram

<img width="806" height="291" alt="ticketmaster_db - seats" src="https://github.com/user-attachments/assets/4dd1f627-9812-42f1-842b-71cc3e340326" />

<img width="503" height="567" alt="ticketmaster_db - bookings" src="https://github.com/user-attachments/assets/d734654f-12fe-4cac-964f-e444381cf80b" />

<img width="527" height="291" alt="ticketmaster_db - events" src="https://github.com/user-attachments/assets/587de790-664a-48ff-a12e-a822b8fa18bc" />





---


