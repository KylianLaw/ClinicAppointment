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