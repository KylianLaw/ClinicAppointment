package com.adeleke.clinicappointment.PatientManagement.BusinessLayer;

import com.adeleke.clinicappointment.PatientManagement.PresentationLayer.PatientRequestModel;
import com.adeleke.clinicappointment.PatientManagement.PresentationLayer.PatientResponseModel;

import java.util.List;

public interface PatientService {

    List<PatientResponseModel> getAllPatients();

    PatientResponseModel getPatientById(String patientId);

    PatientResponseModel updatePatient (String patientId, PatientRequestModel requestModel);

    PatientResponseModel createPatient(PatientRequestModel requestModel);

    void deletePatient(String patientId);
}
