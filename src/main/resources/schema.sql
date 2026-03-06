CREATE TABLE Patients (
                          id INT AUTO_INCREMENT PRIMARY KEY,

    -- Identifier
                          patient_id VARCHAR(255) NOT NULL,

    -- Basic info
                          patient_full_name VARCHAR(255) NOT NULL,
                          birth_date DATE NOT NULL,

    -- Address (Embedded PatientAddress)
                          street VARCHAR(255),
                          city VARCHAR(100),
                          postal_code VARCHAR(20),

    -- Contact Info (Embedded PatientContactInfo)
                          email VARCHAR(255),
                          phone_number VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Doctors (
    id INT NOT NULL AUTO_INCREMENT,
    doctor_id VARCHAR(255) NOT NULL,
    doctor_full_name VARCHAR(255) NOT NULL,
    specialty VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
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