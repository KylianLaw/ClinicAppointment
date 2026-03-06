package com.adeleke.clinicappointment.BusinessLayer.ClinicManagement;

import com.adeleke.clinicappointment.PresentationLayer.ClinicManagement.ClinicRequestModel;
import com.adeleke.clinicappointment.PresentationLayer.ClinicManagement.ClinicResponseModel;

import java.util.List;

public interface ClinicService {

    List<ClinicResponseModel> getAllClinics();

    ClinicResponseModel getClinicById(String clinicId);

    void deleteClinic(String clinicId);

    ClinicResponseModel createClinic(ClinicRequestModel requestModel);

    ClinicResponseModel updateClinic(String clinicId, ClinicRequestModel requestModel);

}
