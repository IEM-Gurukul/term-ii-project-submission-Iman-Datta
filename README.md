[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/pG3gvzt-)

# PCCCS495 – Term II Project

## Project Title

**School Management System** — A Role-Based Academic Record Management Application

---

## Problem Statement

Managing student academic records in a school environment is often error-prone and inefficient when done manually. This project presents a **Role-Based School Management System** built in Java, where three types of users — **Principal**, **Teacher**, and **Student** — interact with the system based on their designated privileges.

The system allows teachers to manage and update marks only for their assigned subjects, while the Principal holds full administrative authority with no subject restrictions. Students can securely register and log in to view only their own marks and results. A persistent database backend ensures that all records are reliably stored and retrieved across sessions, making the system practical for real-world school environments.

---

## Target User

| Role             | Description                                                                    |
| ---------------- | ------------------------------------------------------------------------------ |
| **Principal** | Single super-admin with full access to all student records across all subjects |
| **Teacher**   | Can register/login and manage marks only for their assigned subject(s)         |
| **Student**   | Can register/login and view only their own marks and results                   |

> **Note:** Only **one Principal** account exists in the system. Teachers and Students can self-register.

---

## Core Features

- **User Registration & Authentication** — Teachers and Students can create their own accounts with secure login. The Principal account is pre-seeded in the database.
- **Role-Based Access Control** — Each user type sees only the features and data relevant to their role, enforced at the application level.
- **Mark Entry & Editing (Teacher)** — Teachers can add or update marks for students, but only within their own assigned subject.
- **Unrestricted Principal Access** — The Principal can view, edit, and manage marks for all students across all subjects.
- **Student Result Viewer** — Students can log in and view only their own academic results and subject-wise marks.
- **Persistent Database Storage** — All user data, roles, and marks are stored in a relational database; the app ships with a `.jar` for easy execution.

---

## OOP Concepts Used

- **Abstraction:**
  An abstract class `User` defines the common structure (name, ID, password, role) and declares abstract methods like `showDashboard()` and `getRole()`, which are implemented differently by each subclass.

- **Inheritance:**
  `Teacher`, `Student`, and `Principal` all extend the abstract `User` class, inheriting common attributes and overriding behaviour specific to each role.

- **Polymorphism:**
  Method overriding is used so that calling `showDashboard()` on any `User` reference invokes the correct role-specific dashboard at runtime (runtime polymorphism). Constructor overloading is also used to handle different user initialization scenarios.

- **Exception Handling:**
  Custom exceptions (e.g., `UnauthorizedAccessException`, `SubjectMismatchException`) are thrown when a teacher tries to edit marks outside their subject, or when invalid credentials are entered. All database operations are wrapped in try-catch-finally blocks.

- **Collections / Threads:**
  `ArrayList` and `HashMap` are used to manage lists of students, subjects, and mark records in memory. Background threads may be used for database connection pooling or session timeout handling.

---

## Proposed Architecture Description

The application follows a **layered architecture** with clear separation of concerns:

```
┌─────────────────────────────┐ 
│       Service Layer         │  Business logic, role validation, access control
├─────────────────────────────┤
│         DAO Layer           │  Database access objects (CRUD operations)
├─────────────────────────────┤
│       Database Layer        │  Relational DB (MySQL / SQLite)
└─────────────────────────────┘
```

**Core Classes:**

- `User` _(abstract)_ — base class with shared attributes and abstract methods
- `Student extends User` — can only view own marks
- `Teacher extends User` — can add/edit marks for assigned subject
- `Principal extends User` — full access, single instance enforced
- `DatabaseConnection` — manages DB connectivity using JDBC
- `MarkDAO` — handles all mark-related DB operations
- `UserDAO` — handles registration, login, and role lookup

---

## How to Run

### Prerequisites

- Java JDK 8 or above installed
- A running instance of MySQL / SQLite (as configured)
- JDBC driver included in the `.jar`

### Steps

1. **Clone the repository:**

   ```bash
   git clone https://github.com/IEM-Gurukul/term-ii-project-submission-Iman-Datta.git
   cd term-ii-project-submission-Iman-Datta
   ```

2. **Set up the database:**
   - Import the provided SQL schema file (e.g., `schema.sql`) into your database:
     ```bash
     mysql -u root -p < schema.sql
     ```
   - Update the DB credentials in the config file if required.

3. **Run the application:**

   ```bash
   java -jar SchoolManagementSystem.jar
   ```

4. **Login options:**
   - Register as a **Teacher** or **Student** from the main menu.
   - Use the pre-configured **Principal** credentials to access admin features.

---

## Git Discipline Notes

- Minimum **10 meaningful commits** required throughout development.
- Each commit should reflect a logical, incremental change (e.g., `Add User abstract class`, `Implement Teacher login`, `Connect DB with DAO layer`, `Add mark entry feature`, etc.).
- Avoid bulk commits — commit after completing each feature or fix.
- Use clear, descriptive commit messages in the imperative tense.
