# StudentManagmentSystem
A Java console application to manage student records using JDBC and MySQL. Supports CRUD operations and fetching student details by ID.


# Student Management System in Java

This is a **Java console application** for managing student records using **JDBC** and **MySQL**. The application allows you to create a table, insert new students, update or delete student details, and fetch student information by ID.

---

## Features
- Create the student table automatically.
- Add new student records.
- Update existing student details.
- Delete students by ID.
- Fetch a student’s details by ID.

---

## Database Structure

### Table: `mystud111`

| Column Name | Data Type       | Key          | Description                 |
|-------------|----------------|-------------|-----------------------------|
| stdId       | VARCHAR(30)    | PRIMARY KEY | Unique ID for each student |
| stdname     | VARCHAR(50)    |             | Student’s full name        |
| stdmail     | VARCHAR(50)    |             | Student email address      |
| stddob      | VARCHAR(30)    |             | Student date of birth      |

**SQL to create table manually:**
```sql
CREATE TABLE IF NOT EXISTS mystud111 (
    stdId VARCHAR(30) PRIMARY KEY,
    stdname VARCHAR(50),
    stdmail VARCHAR(50),
    stddob VARCHAR(30)
);

