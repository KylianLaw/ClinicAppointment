------------------------------------------------
-- PATIENTS
------------------------------------------------
DELETE FROM Clinics;
DELETE FROM Doctors;
DELETE FROM Patients;
INSERT INTO Patients (patient_id, patient_full_name, birth_date, street, city, postal_code, email, phone_number)
VALUES ('PAT-001', 'John Smith', '1985-03-15', '123 Maple St', 'Montreal', 'H2X1A1', 'john.smith@email.com', '514-555-1001');

INSERT INTO Patients VALUES
                         (NULL,'PAT-002','Emily Johnson','1992-07-21','456 Oak Ave','Longueuil','J4K2P4','emily.johnson@email.com','514-555-1002'),
                         (NULL,'PAT-003','Michael Brown','1978-11-05','789 Pine Rd','Brossard','J4Z3T2','michael.brown@email.com','514-555-1003'),
                         (NULL,'PAT-004','Sarah Davis','1990-04-10','321 Birch St','Laval','H7A4K2','sarah.davis@email.com','514-555-1004'),
                         (NULL,'PAT-005','David Wilson','1982-09-18','654 Cedar Blvd','Montreal','H3Z2Y7','david.wilson@email.com','514-555-1005'),
                         (NULL,'PAT-006','Olivia Martinez','1995-01-30','987 Spruce Dr','Brossard','J4W1K6','olivia.m@email.com','514-555-1006'),
                         (NULL,'PAT-007','Daniel Anderson','1975-06-12','147 Willow Ln','Longueuil','J4L3A5','daniel.a@email.com','514-555-1007'),
                         (NULL,'PAT-008','Sophia Thomas','2000-02-22','258 Cherry St','Montreal','H1A2B3','sophia.t@email.com','514-555-1008'),
                         (NULL,'PAT-009','James Taylor','1988-08-14','369 Walnut Rd','Laval','H7P5M2','james.t@email.com','514-555-1009'),
                         (NULL,'PAT-010','Emma White','1997-12-03','159 Poplar St','Brossard','J4Y2S7','emma.white@email.com','514-555-1010');

------------------------------------------------
-- DOCTORS
------------------------------------------------

INSERT INTO Doctors (doctor_id, doctor_full_name, specialty)
VALUES ('DOC-001','Dr. Laura Green','CARDIOLOGY');

INSERT INTO Doctors VALUES
                        (NULL,'DOC-002','Dr. Robert Hall','DERMATOLOGY'),
                        (NULL,'DOC-003','Dr. Angela Young','PEDIATRICS'),
                        (NULL,'DOC-004','Dr. Kevin King','ORTHOPEDICS'),
                        (NULL,'DOC-005','Dr. Rachel Scott','NEUROLOGY'),
                        (NULL,'DOC-006','Dr. Brian Adams','FAMILY_MEDICINE'),
                        (NULL,'DOC-007','Dr. Jessica Baker','GYNECOLOGY'),
                        (NULL,'DOC-008','Dr. Steven Nelson','PSYCHIATRY'),
                        (NULL,'DOC-009','Dr. Natalie Carter','ENDOCRINOLOGY'),
                        (NULL,'DOC-010','Dr. Matthew Mitchell','RADIOLOGY');

------------------------------------------------
-- CLINICS
------------------------------------------------

INSERT INTO Clinics (clinic_id, clinic_name, street, city, postal_code)
VALUES ('CLINIC-001','Downtown Medical Center','1200 St Catherine St','Montreal','H3B1K5');

INSERT INTO Clinics VALUES
                        (NULL,'CLINIC-002','Brossard Family Clinic','5000 Taschereau Blvd','Brossard','J4Z1A7'),
                        (NULL,'CLINIC-003','Longueuil Health Clinic','825 Chemin Chambly','Longueuil','J4H3M1'),
                        (NULL,'CLINIC-004','Laval Medical Group','1000 Boulevard Curé-Labelle','Laval','H7V3X7'),
                        (NULL,'CLINIC-005','West Island Clinic','2000 Boulevard St Jean','Pointe-Claire','H9R5M9'),
                        (NULL,'CLINIC-006','Plateau Medical Clinic','3500 Rue St Denis','Montreal','H2X3L7'),
                        (NULL,'CLINIC-007','South Shore Health Center','4200 Boulevard Matte','Brossard','J4Y2P4'),
                        (NULL,'CLINIC-008','Montreal Pediatric Clinic','2100 Sherbrooke St','Montreal','H2K1C3'),
                        (NULL,'CLINIC-009','Laval Family Care','1450 Boulevard Concorde','Laval','H7G2E5'),
                        (NULL,'CLINIC-010','Urban Wellness Clinic','1800 Boulevard Rene-Levesque','Montreal','H3H2P3');