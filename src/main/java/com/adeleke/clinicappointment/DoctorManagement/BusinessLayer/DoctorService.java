package com.adeleke.clinicappointment.DoctorManagement.BusinessLayer;

import com.adeleke.clinicappointment.DoctorManagement.PresentationLayer.DoctorRequestModel;
import com.adeleke.clinicappointment.DoctorManagement.PresentationLayer.DoctorResponseModel;

import java.util.List;

public interface DoctorService {

    List<DoctorResponseModel> getAllDoctors();

    DoctorResponseModel getDoctorById(String doctorId);

    DoctorResponseModel createDoctor(DoctorRequestModel requestModel);

    DoctorResponseModel updateDoctor(String doctorId, DoctorRequestModel requestModel);

    void deleteDoctor(String doctorId);
}
