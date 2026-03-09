# Clinic Appointment Booking System

## Overview

The **Clinic Appointment Booking System** is a Spring Boot REST API designed using **Domain-Driven Design (DDD)** principles to manage appointments in a clinical environment.

The system is organized into multiple subdomains:

* **Patient Management** (Supporting)
* **Doctor Management** (Supporting)
* **Clinic Management** (Supporting)
* **Appointment Management** (Core)

The purpose of the system is to allow scheduling and management of appointments and follow-up plans by connecting **patients, doctors, and clinics** through a layered architecture and RESTful API.

---

# Technologies Used

This project uses the following technologies:

* **Java 17**
* **Spring Boot 3.5.4**
* **Spring Web**
* **Spring Data JPA**
* **Spring Data JDBC**
* **Spring HATEOAS**
* **MySQL 8**
* **H2 Database (testing/runtime)**
* **MapStruct**
* **Lombok**
* **Docker**
* **Docker Compose**
* **phpMyAdmin**
* **JUnit 5**
* **JaCoCo**

---

# Architecture

The project follows a **Domain-Driven Design inspired layered architecture**.

Each subdomain is divided into the following layers:

```
PresentationLayer
BusinessLayer
DataLayer
MappingLayer
```

### Request Flow

```
Controller -> Service -> Repository
```

This ensures separation of concerns and prevents controllers from directly accessing the database layer.

---

# Bounded Context and Subdomains

## Supporting Subdomains

### Patient Management

The patient aggregate contains:

* patient identifier
* full name
* birth date
* address
* contact information

**Invariants**

* Patient full name cannot be null or blank
* Birth date cannot be null
* Patient address must be provided
* Patient contact info must be provided
* Duplicate patient must not exist

---

### Doctor Management

The doctor aggregate contains:

* doctor identifier
* full name
* specialty
* contact information

**Invariants**

* Doctor full name cannot be null or blank
* Specialty cannot be null
* Duplicate doctor must not exist

---

### Clinic Management

The clinic aggregate contains:

* clinic identifier
* clinic name
* clinic address

**Invariants**

* Clinic name cannot be null or blank
* Clinic address must be provided
* Street, city, and postal code cannot be null or blank
* Duplicate clinic must not exist

---

## Core Subdomain

### Appointment Management

The appointment aggregate contains:

* appointment identifier
* patient identifier
* doctor identifier
* clinic identifier
* appointment date and time
* appointment status

**Invariants**

* Appointment cannot be scheduled in the past
* Patient cannot have overlapping appointments
* A cancelled appointment cannot become completed

---

### Appointment Follow-Up Plan

The follow-up plan aggregate contains:

* followUpPlan identifier
* appointment identifier
* patient identifier
* doctor identifier
* clinic identifier
* recommended follow-up date
* follow-up reason
* follow-up status

**Invariants**

* Follow-up plan must reference a valid appointment
* Follow-up date must be after the original appointment date
* A completed follow-up plan cannot become planned again
* A cancelled follow-up plan cannot become completed

---

# REST API

## Base URLs

```
/api/v1/patients
/api/v1/doctors
/api/v1/clinics
/api/v1/appointments
/api/v1/follow-up-plans
```

---

# Patient Endpoints

| Method | Endpoint                     | Description       |
|------|-----------------------------|-----------------|
| POST | /api/v1/patients | Create patient |
| GET | /api/v1/patients/{patientId} | Get patient by id |
| GET | /api/v1/patients | Get all patients |
| PUT | /api/v1/patients/{patientId} | Update patient |
| DELETE | /api/v1/patients/{patientId} | Delete patient |

---

# Doctor Endpoints

| Method | Endpoint | Description |
|------|-----------|-------------|
| POST | /api/v1/doctors | Create doctor |
| GET | /api/v1/doctors/{doctorId} | Get doctor by id |
| GET | /api/v1/doctors | Get all doctors |
| PUT | /api/v1/doctors/{doctorId} | Update doctor |
| DELETE | /api/v1/doctors/{doctorId} | Delete doctor |

---

# Clinic Endpoints

| Method | Endpoint | Description |
|------|-----------|-------------|
| POST | /api/v1/clinics | Create clinic |
| GET | /api/v1/clinics/{clinicId} | Get clinic by id |
| GET | /api/v1/clinics | Get all clinics |
| PUT | /api/v1/clinics/{clinicId} | Update clinic |
| DELETE | /api/v1/clinics/{clinicId} | Delete clinic |

---

# Appointment Endpoints

| Method | Endpoint | Description |
|------|-----------|-------------|
| POST | /api/v1/appointments | Create appointment |
| GET | /api/v1/appointments/{appointmentId} | Get appointment |
| GET | /api/v1/appointments | Get all appointments |
| PUT | /api/v1/appointments/{appointmentId} | Update appointment |
| DELETE | /api/v1/appointments/{appointmentId} | Delete appointment |
| PUT | /api/v1/appointments/{appointmentId}/cancel | Cancel appointment |
| PUT | /api/v1/appointments/{appointmentId}/complete | Complete appointment |

---

# Follow-Up Plan Endpoints

| Method | Endpoint | Description |
|------|-----------|-------------|
| POST | /api/v1/follow-up-plans | Create follow-up plan |
| GET | /api/v1/follow-up-plans/{followUpPlanId} | Get follow-up plan |
| GET | /api/v1/follow-up-plans | Get all follow-up plans |
| PUT | /api/v1/follow-up-plans/{followUpPlanId} | Update follow-up plan |
| DELETE | /api/v1/follow-up-plans/{followUpPlanId} | Delete follow-up plan |
| PUT | /api/v1/follow-up-plans/{followUpPlanId}/complete | Complete follow-up plan |
| PUT | /api/v1/follow-up-plans/{followUpPlanId}/cancel | Cancel follow-up plan |

---

# Example Appointment Request

```json
{
  "patientId": "PAT-001",
  "doctorId": "DOC-001",
  "clinicId": "CLN-001",
  "appointmentDateTime": "2026-03-08T10:30:00"
}
```

---

# Example Appointment Response

```json
{
  "appointmentId": "APT-001",
  "appointmentDateTime": "2026-03-08T10:30:00",
  "appointmentStatus": "SCHEDULED",
  "patientId": "PAT-001",
  "patientFullName": "John Doe",
  "doctorId": "DOC-001",
  "doctorFullName": "Dr Jane Smith",
  "specialty": "FAMILY_MEDICINE",
  "clinicId": "CLN-001",
  "clinicName": "Downtown Clinic",
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/v1/appointments/APT-001"
    }
  }
}
```

---

# DTO and HATEOAS

The API uses **DTOs (Data Transfer Objects)** instead of exposing entities directly.

Examples:

* AppointmentRequestModel
* AppointmentResponseModel
* AppointmentFollowUpPlanRequestModel
* AppointmentFollowUpPlanResponseModel
* PatientResponseModel
* DoctorResponseModel
* ClinicResponseModel

Responses use **Spring HATEOAS** via:

```
EntityModel<>
RepresentationModel<>
```

This allows hypermedia links to be embedded into responses.

---

# Docker Setup

The project runs using **Docker Compose**.

### Services

**clinic_application**

Spring Boot API

Port:

```
8080
```

**mysql**

MySQL database

Port:

```
3307
```

Database:

```
clinic_app
```

**phpmyadmin**

Database web interface

Port:

```
5013
```

---

# Running the Project

## Start the application

```bash
docker compose up --build
```

## Stop the application

```bash
docker compose down
```

---

# Accessing the System

### API

```
http://localhost:8080
```

### phpMyAdmin

```
http://localhost:5013
```

### MySQL Connection

Host

```
localhost
```

Port

```
3307
```

Database

```
clinic_app
```

Username

```
user
```

Password

```
pwd
```

---

# Persistence

Docker volumes are used for persistence:

```
./data/mysql:/var/lib/mysql
./data/init.d:/docker-entrypoint-initdb.d
```

---

# Testing

Tests use:

* Spring Boot Test
* JUnit 5
* H2 database
* JaCoCo for coverage

Run tests:

```bash
./gradlew test
```

Generate coverage report:

```bash
./gradlew jacocoTestReport
```

---

# Build

Build the project:

```bash
./gradlew build
```

Run locally:

```bash
./gradlew bootRun
```

---

# Project Highlights

* Domain-Driven Design architecture
* Supporting and core subdomains
* Layered architecture
* RESTful API
* DTO-based design
* HATEOAS support
* Dockerized environment
* MySQL persistence
* phpMyAdmin database management
* Aggregate invariants
* Test coverage with JaCoCo

---

# Future Improvements

* Swagger / OpenAPI documentation
* Integration testing
* CI/CD pipeline
* Authentication and authorization
* API versioning strategy
* Monitoring with Spring Actuator

---

# Team

Add team members here.

Example

```
Ilian Adeleke
Santiago Arevalo
```

---

# Project Description

ClinicAppointment is a Spring Boot application implementing a DDD-based clinic scheduling system with supporting subdomains for patient, doctor, and clinic management and a core appointment management subdomain responsible for scheduling and follow-up coordination.
