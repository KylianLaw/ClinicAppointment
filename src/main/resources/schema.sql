DROP TABLE IF EXISTS appointment_follow_up_plan;
DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS Patients;
DROP TABLE IF EXISTS Doctors;
DROP TABLE IF EXISTS Clinics;


------------------------------------------------
-- PATIENTS
------------------------------------------------

CREATE TABLE IF NOT EXISTS Patients (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        patient_id VARCHAR(255) NOT NULL,
    patient_full_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,

    street VARCHAR(255),
    city VARCHAR(100),
    postal_code VARCHAR(20),

    email VARCHAR(255),
    phone_number VARCHAR(50),

    UNIQUE KEY uk_patient_identifier (patient_id)
    );

------------------------------------------------
-- DOCTORS
------------------------------------------------

CREATE TABLE IF NOT EXISTS Doctors (
                                       id INT NOT NULL AUTO_INCREMENT,
                                       doctor_id VARCHAR(255) NOT NULL,
    doctor_full_name VARCHAR(255) NOT NULL,
    specialty VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),
    UNIQUE KEY uk_doctor_identifier (doctor_id)
    );

------------------------------------------------
-- CLINICS
------------------------------------------------

CREATE TABLE IF NOT EXISTS Clinics (
                                       id INT NOT NULL AUTO_INCREMENT,
                                       clinic_id VARCHAR(255) NOT NULL,
    clinic_name VARCHAR(255) NOT NULL,

    street VARCHAR(255),
    city VARCHAR(255),
    postal_code VARCHAR(50),

    PRIMARY KEY (id),
    UNIQUE KEY uk_clinic_identifier (clinic_id)
    );


------------------------------------------------
-- APPOINTMENTS
------------------------------------------------

CREATE TABLE IF NOT EXISTS appointments (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            appointment_id VARCHAR(255) NOT NULL,
    doctor_id VARCHAR(255) NOT NULL,
    patient_id VARCHAR(255) NOT NULL,
    clinic_id VARCHAR(255) NOT NULL,
    appointment_date_time DATETIME NOT NULL,
    appointment_status VARCHAR(50) NOT NULL,

    UNIQUE KEY uk_appointment_identifier (appointment_id),

    CONSTRAINT fk_appointment_patient
    FOREIGN KEY (patient_id) REFERENCES Patients(patient_id),

    CONSTRAINT fk_appointment_doctor
    FOREIGN KEY (doctor_id) REFERENCES Doctors(doctor_id),

    CONSTRAINT fk_appointment_clinic
    FOREIGN KEY (clinic_id) REFERENCES Clinics(clinic_id)
    );

------------------------------------------------
-- APPOINTMENTS FOLLOW-UP PLANS
------------------------------------------------

CREATE TABLE IF NOT EXISTS appointment_follow_up_plan (
                                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                                          follow_up_plan_id VARCHAR(255) NOT NULL,
    appointment_id VARCHAR(255) NOT NULL,
    patient_id VARCHAR(255) NOT NULL,
    doctor_id VARCHAR(255) NOT NULL,
    clinic_id VARCHAR(255) NOT NULL,
    follow_up_reason VARCHAR(255) NOT NULL,
    recommended_follow_up_date DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,

    UNIQUE KEY uk_follow_up_plan_identifier (follow_up_plan_id),

    CONSTRAINT fk_follow_up_plan_appointment
    FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id),

    CONSTRAINT fk_follow_up_plan_patient
    FOREIGN KEY (patient_id) REFERENCES Patients(patient_id),

    CONSTRAINT fk_follow_up_plan_doctor
    FOREIGN KEY (doctor_id) REFERENCES Doctors(doctor_id),

    CONSTRAINT fk_follow_up_plan_clinic
    FOREIGN KEY (clinic_id) REFERENCES Clinics(clinic_id)
    );