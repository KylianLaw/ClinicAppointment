-- CREATE TABLE IF NOT EXISTS Patients (
--                           id INT AUTO_INCREMENT PRIMARY KEY,
--
--     -- Identifier
--                           patient_id VARCHAR(255) NOT NULL,
--
--     -- Basic info
--                           patient_full_name VARCHAR(255) NOT NULL,
--                           birth_date DATE NOT NULL,
--
--     -- Address (Embedded PatientAddress)
--                           street VARCHAR(255),
--                           city VARCHAR(100),
--                           postal_code VARCHAR(20),
--
--     -- Contact Info (Embedded PatientContactInfo)
--                           email VARCHAR(255),
--                           phone_number VARCHAR(50)
-- );
--
-- CREATE TABLE IF NOT EXISTS Doctors (
--     id INT NOT NULL AUTO_INCREMENT,
--     doctor_id VARCHAR(255) NOT NULL,
--     doctor_full_name VARCHAR(255) NOT NULL,
--     specialty VARCHAR(255) NOT NULL,
--     PRIMARY KEY (id)
--     );
--
-- CREATE TABLE IF NOT EXISTS Clinics (
--     id INT NOT NULL AUTO_INCREMENT,
--     clinic_id VARCHAR(255) NOT NULL,
--     clinic_name VARCHAR(255) NOT NULL,
--     street VARCHAR(255),
--     city VARCHAR(255),
--     postal_code VARCHAR(50),
--     PRIMARY KEY (id),
--     UNIQUE KEY uk_clinic_identifier (clinic_id)
--     );

DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS Patients;
DROP TABLE IF EXISTS Doctors;
DROP TABLE IF EXISTS Clinics;


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

CREATE TABLE IF NOT EXISTS Doctors (
                                       id INT NOT NULL AUTO_INCREMENT,
                                       doctor_id VARCHAR(255) NOT NULL,
    doctor_full_name VARCHAR(255) NOT NULL,
    specialty VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_doctor_identifier (doctor_id)
    );

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