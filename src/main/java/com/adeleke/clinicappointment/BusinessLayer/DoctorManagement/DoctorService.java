package com.adeleke.clinicappointment.BusinessLayer.DoctorManagement;

import com.adeleke.clinicappointment.PresentationLayer.DoctorManagement.DoctorRequestModel;
import com.adeleke.clinicappointment.PresentationLayer.DoctorManagement.DoctorResponseModel;

import java.util.List;

public interface DoctorService {

    List<DoctorResponseModel> getAllDoctors();

    DoctorResponseModel getDoctorById(String doctorId);

    DoctorResponseModel createDoctor(DoctorRequestModel requestModel);

    DoctorResponseModel updateDoctor(String doctorId, DoctorRequestModel requestModel);

    void deleteDoctor(String doctorId);
}
