package com.adeleke.clinicappointment.BusinessLayer.PatientManagement;

import com.adeleke.clinicappointment.PresentationLayer.PatientManagement.PatientRequestModel;
import com.adeleke.clinicappointment.PresentationLayer.PatientManagement.PatientResponseModel;

import java.util.List;

public interface PatientService {

    List<PatientResponseModel> getAllPatients();

    PatientResponseModel getPatientById(String patientId);

    PatientResponseModel updatePatient (String patientId, PatientRequestModel requestModel);

    PatientResponseModel createPatient(PatientRequestModel requestModel);

    void deletePatient(String patientId);
}
