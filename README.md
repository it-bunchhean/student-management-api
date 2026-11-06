# Student Management System (University Level)

## Project Overview

The Student Management System is a backend application designed for university-level academic management.  
It handles student information, course management, enrollment, grading, and secure access control using JWT authentication and role-based authorization.

This system is built using Spring Boot and follows a layered architecture with security, academic core, and operational modules.

---

## System Architecture

The system is divided into three main layers:

---

### 1. Security Layer (Authentication and Authorization)

This layer manages system access and security.

It includes:

- Users (login accounts)
- Roles (ADMIN, STUDENT, LECTURER, STAFF)
- Permissions (fine-grained actions)
- JWT authentication
- Role-based and permission-based access control

#### Concept:

- Users authenticate using username and password  
- System issues a JWT token  
- Access is controlled using roles and permissions  

---

### 2. Academic Core Layer

This layer defines the university structure.

It includes:

- Departments  
- Courses  
- Academic Years  
- Semesters  

---

### 3. Academic Operation Layer

This layer manages student academic activities.

It includes:

- Students  
- Teachers  
- Course Offerings (course per semester with teacher assignment)  
- Sections (class groups)  
- Enrollments  
- Grades  

---

## Database Design Order

The tables must be created in the following order to ensure proper relationships:

---

### Step 1: Security Core
- users  
- roles  
- permissions  

---

### Step 2: Security Mapping
- user_roles  
- role_permissions  

---

### Step 3: Academic Base
- departments  
- academic_years  
- semesters  

---

### Step 4: Course System
- courses  
- teachers  

---

### Step 5: Students
- students  

---

### Step 6: Teaching Structure
- course_offerings  
- sections  

---

### Step 7: Academic Operations
- enrollments  
- grades  

---

## Authentication and Authorization

### Authentication

The system uses JWT-based authentication.

#### Flow:

1. User logs in with username and password  
2. System validates credentials  
3. JWT token is generated  
4. Token is used for accessing secured APIs  

---

### Authorization

The system uses Role-Based Access Control (RBAC) combined with Permissions.

---

#### Roles

- ADMIN  
- LECTURER  
- STAFF  
- STUDENT  

---

#### Permissions (Examples)

- STUDENT_VIEW  
- STUDENT_CREATE  
- COURSE_MANAGE  
- ENROLL_APPROVE  
- GRADE_SUBMIT  

---

Roles are assigned to users, and permissions are assigned to roles.

---

## User Access Rules

### Student
- View courses  
- Enroll in sections  
- View own grades only  

---

### Lecturer
- View assigned classes  
- Submit grades  
- Manage sections  

---

### Staff
- Manage enrollments  
- Manage student records  
- Handle registration process  

---

### Admin
- Manage users  
- Manage roles and permissions  
- Full system access  

---

## Tech Stack

- Java 17+  
- Spring Boot  
- Spring Security  
- JWT Authentication  
- Spring Data JPA  
- MySQL or PostgreSQL  
- Lombok  
- MapStruct (optional)  
- Swagger for API documentation  

---

## Development Steps

### Step 1: Project Setup
- Create Spring Boot project  
- Add required dependencies  

---

### Step 2: Security Implementation
- Create User entity  
- Implement Role and Permission system  
- Implement JWT authentication  
- Secure APIs  

---

### Step 3: Academic Core
- Department  
- Course  
- Academic Year  
- Semester  

---

### Step 4: User Profiles
- Student entity linked to User  
- Teacher entity linked to User  

---

### Step 5: Academic Operations
- Course Offering  
- Section  
- Enrollment  
- Grade  

---

### Step 6: Testing
- Test APIs using Postman  
- Validate role and permission access  
- Verify JWT security  
