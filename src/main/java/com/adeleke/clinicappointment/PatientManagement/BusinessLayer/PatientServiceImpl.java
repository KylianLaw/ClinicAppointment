package com.adeleke.clinicappointment.PatientManagement.BusinessLayer;

import com.adeleke.clinicappointment.Exception.exceptions.DuplicatePatientException;
import com.adeleke.clinicappointment.PatientManagement.DataLayer.Patient;
import com.adeleke.clinicappointment.PatientManagement.DataLayer.PatientIdentifier;
import com.adeleke.clinicappointment.PatientManagement.DataLayer.PatientRepository;
import com.adeleke.clinicappointment.Exception.exceptions.InvalidInputException;
import com.adeleke.clinicappointment.Exception.exceptions.NotFoundException;
import com.adeleke.clinicappointment.PatientManagement.PresentationLayer.PatientRequestModel;
import com.adeleke.clinicappointment.PatientManagement.PresentationLayer.PatientResponseModel;
import com.adeleke.clinicappointment.PatientManagement.MappingLayer.PatientRequestMapper;
import com.adeleke.clinicappointment.PatientManagement.MappingLayer.PatientResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientRequestMapper requestMapper;
    private final PatientResponseMapper responseMapper;

    public PatientServiceImpl(PatientRepository patientRepository,
                              PatientRequestMapper requestMapper,
                              PatientResponseMapper responseMapper) {
        this.patientRepository = patientRepository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Override
    public List<PatientResponseModel> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return responseMapper.entityListToRespondModel(patients);
    }

    @Override
    public PatientResponseModel getPatientById(String patientId) {
        if (patientId == null || patientId.isBlank()) {
            throw new InvalidInputException("Patient id cannot be null or empty");
        }

        Patient patient = patientRepository.findByPatientId_PatientId(patientId);
        if (patient == null) {
            throw new NotFoundException("Patient with ID " + patientId + " not found");
        }

        return responseMapper.entityToResponseModel(patient);
    }

    @Override
    public PatientResponseModel updatePatient(String patientId, PatientRequestModel requestModel) {
        if (patientId == null || patientId.isBlank()) {
            throw new InvalidInputException("Patient id cannot be null or empty");
        }

        if (requestModel == null) {
            throw new InvalidInputException("Patient request model cannot be null");
        }

        Patient patient = patientRepository.findByPatientId_PatientId(patientId);
        if (patient == null) {
            throw new NotFoundException("Patient with ID " + patientId + " not found");
        }

        if (requestModel.getPatientFullName() == null || requestModel.getPatientFullName().isBlank()) {
            throw new InvalidInputException("Patient full name cannot be null or empty");
        }

        if (requestModel.getBirthDate() == null) {
            throw new InvalidInputException("Birth date cannot be null");
        }

        if (requestModel.getAddressRequest() == null) {
            throw new InvalidInputException("Patient address cannot be null");
        }

        if (requestModel.getContactInfoRequest() == null) {
            throw new InvalidInputException("Patient contact info cannot be null");
        }

        // 409 conflict check


        Patient existingPatient = patientRepository
                .findByPatientFullNameAndBirthDate(
                        requestModel.getPatientFullName(),
                        requestModel.getBirthDate()
                );

        if (existingPatient != null &&
                !existingPatient.getPatientId().getPatientId().equals(patientId)) {
            throw new DuplicatePatientException(
                    "Another patient already exists with the same full name and birth date"
            );
        }
        // -----------------------------------------------------------

        patient.setPatientFullName(requestModel.getPatientFullName());
        patient.setPatientAddress(requestModel.getAddressRequest());
        patient.setPatientContactInfo(requestModel.getContactInfoRequest());
        patient.setBirthDate(requestModel.getBirthDate());

        Patient updatedPatient = patientRepository.save(patient);
        return responseMapper.entityToResponseModel(updatedPatient);
    }

    @Override
    public PatientResponseModel createPatient(PatientRequestModel requestModel) {
        if (requestModel == null) {
            throw new InvalidInputException("Patient request model cannot be null");
        }

        if (requestModel.getPatientFullName() == null || requestModel.getPatientFullName().isBlank()) {
            throw new InvalidInputException("Patient full name cannot be null or empty");
        }

        if (requestModel.getBirthDate() == null) {
            throw new InvalidInputException("Birth date cannot be null");
        }

        if (requestModel.getAddressRequest() == null) {
            throw new InvalidInputException("Patient address cannot be null");
        }

        if (requestModel.getContactInfoRequest() == null) {
            throw new InvalidInputException("Patient contact info cannot be null");
        }
        // 409 conflict check
        Patient existingPatient = patientRepository
                .findByPatientFullNameAndBirthDate(
                        requestModel.getPatientFullName(),
                        requestModel.getBirthDate()
                );

        if (existingPatient != null) {
            throw new DuplicatePatientException(
                    "Patient already exists with the same full name and birth date"
            );
        }
// ------------------------------------------
        Patient patientEntity = requestMapper.toEntity(requestModel, new PatientIdentifier());
        Patient savedPatient = patientRepository.save(patientEntity);
        return responseMapper.entityToResponseModel(savedPatient);
    }

    @Override
    public void deletePatient(String patientId) {

        if (patientId == null || patientId.isBlank()) {
            throw new InvalidInputException("Patient id cannot be null or empty");
        }

        Patient patient = patientRepository.findByPatientId_PatientId(patientId);

        if (patient == null) {
            throw new NotFoundException("Patient with ID " + patientId + " not found");
        }

        /*
         * FUTURE BUSINESS RULE (CORE DOMAIN INTEGRATION)
         *
         * A patient cannot be deleted if they have existing appointments.
         * This will be enforced once Appointment Management is implemented.
         *
         * Example implementation later:
         *
         * if(appointmentRepository.existsByPatientId(patientId)) {
         *     throw new DuplicatePatientException(
         *         "Patient cannot be deleted because there are existing appointments linked to this patient"
         *     );
         * }
         */

        patientRepository.delete(patient);
    }
}