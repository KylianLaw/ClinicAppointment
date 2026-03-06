package com.adeleke.clinicappointment.ClinicManagement.BusinessLayer;

import com.adeleke.clinicappointment.ClinicManagement.PresentationLayer.ClinicRequestModel;
import com.adeleke.clinicappointment.ClinicManagement.PresentationLayer.ClinicResponseModel;

import java.util.List;

public interface ClinicService {

    List<ClinicResponseModel> getAllClinics();

    ClinicResponseModel getClinicById(String clinicId);

    void deleteClinic(String clinicId);

    ClinicResponseModel createClinic(ClinicRequestModel requestModel);

    ClinicResponseModel updateClinic(String clinicId, ClinicRequestModel requestModel);

}
