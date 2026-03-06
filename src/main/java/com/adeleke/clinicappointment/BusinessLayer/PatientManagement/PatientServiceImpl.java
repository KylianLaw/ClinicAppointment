package com.adeleke.clinicappointment.BusinessLayer.PatientManagement;

import com.adeleke.clinicappointment.DataLayer.PatientManegement.Patient;
import com.adeleke.clinicappointment.DataLayer.PatientManegement.PatientIdentifier;
import com.adeleke.clinicappointment.DataLayer.PatientManegement.PatientRepository;
import com.adeleke.clinicappointment.Exception.exceptions.InvalidInputException;
import com.adeleke.clinicappointment.Exception.exceptions.NotFoundException;
import com.adeleke.clinicappointment.PresentationLayer.PatientManagement.PatientRequestModel;
import com.adeleke.clinicappointment.PresentationLayer.PatientManagement.PatientResponseModel;
import com.adeleke.clinicappointment.mappingLayer.PatientManagement.PatientRequestMapper;
import com.adeleke.clinicappointment.mappingLayer.PatientManagement.PatientResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private final PatientRequestMapper requestMapper;

    private final PatientResponseMapper responseMapper;

    public PatientServiceImpl(PatientRepository patientRepository, PatientRequestMapper requestMapper, PatientResponseMapper responseMapper) {
        this.patientRepository = patientRepository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Override
    public java.util.List<PatientResponseModel> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();

        return null;
    }

    @Override
    public PatientResponseModel getPatientById(String patientId) {
        Patient patient = patientRepository.findByPatientId(patientId);
        if(patient == null) {
            throw new NotFoundException("Patient with ID " + patientId + " not found");
        }
         return responseMapper.entityToResponseModel(patient);
        }


    @Override
    public PatientResponseModel updatePatient(String patientId, PatientRequestModel requestModel) {
        Patient patient = patientRepository.findByPatientId(patientId);
        if ( patient == null) {
            throw new NotFoundException("Patient with ID " + patientId + " not found");
        }
        if (requestModel == null) {
            throw new InvalidInputException("Patient request model cannot be null");
        }
        patient.setPatientFullName(requestModel.getPatientFullName());
        patient.setPatientAddress(requestModel.getAddressRequest());
        patient.setPatientContactInfo(requestModel.getContactInfoRequest());
        patient.setBirthDate(requestModel.getBirthDate());
        patient.setPatientContactInfo(requestModel.getContactInfoRequest());
        Patient updatedPatient = patientRepository.save(patient);
        return responseMapper.entityToResponseModel(updatedPatient);
    }

    @Override
    public PatientResponseModel createPatient(PatientRequestModel requestModel) {
        if (requestModel == null){
            throw new InvalidInputException("Patient request model cannot be null");
        }
        Patient patientEntity = requestMapper.toEntity(requestModel, new PatientIdentifier());
        Patient savedPatient = patientRepository.save(patientEntity);
        return responseMapper.entityToResponseModel(savedPatient);
    }

    @Override
    public void deletePatient(String patientId) {
        Patient patient = patientRepository.findByPatientId(patientId);
        if (patient == null) {
            throw new NotFoundException("Patient with ID " + patientId + " not found");
        }
        patientRepository.delete(patient);
    }
}
