package com.adeleke.clinicappointment.AppointmentManagement.BusinessLayer;


import com.adeleke.clinicappointment.AppointmentManagement.PresentationLayer.AppointmentRequestModel;
import com.adeleke.clinicappointment.AppointmentManagement.PresentationLayer.AppointmentResponseModel;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    List<AppointmentResponseModel> getAllAppointments();

    AppointmentResponseModel getAppointmentById(String appointmentId);

    List<AppointmentResponseModel> getAppointmentsByPatientId(String patientId1);

    List<AppointmentResponseModel> getAppointmentsByDoctorId(String doctorId);

    List<AppointmentResponseModel> getAppointmentsByClinicId(String clinicId);

    AppointmentResponseModel createAppointment(String appointmentId, String doctorId, String patientId, String clinicId, LocalDateTime appointmentDateTime);

    AppointmentResponseModel updateAppointment(String appointmentId, AppointmentRequestModel appointmentRequestModel);

    void deleteAppointment(String appointmentId);

    AppointmentResponseModel cancelAppointment(String appointmentId);

    AppointmentResponseModel completeAppointment(String  appointmentId);


}
