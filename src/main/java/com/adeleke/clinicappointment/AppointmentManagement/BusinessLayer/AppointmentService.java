package com.adeleke.clinicappointment.AppointmentManagement.BusinessLayer;


import com.adeleke.clinicappointment.AppointmentManagement.PresentationLayer.AppointmentRequestModel;
import com.adeleke.clinicappointment.AppointmentManagement.PresentationLayer.AppointmentResponseModel;

import java.util.List;

public interface AppointmentService {

    List<AppointmentResponseModel> getAllAppointments();

    AppointmentResponseModel getAppointmentById(String appointmentId);

    AppointmentResponseModel createAppointment(AppointmentRequestModel requestModel);

    AppointmentResponseModel updateAppointment(String appointmentId, AppointmentRequestModel requestModel);

    void deleteAppointment(String appointmentId);

    AppointmentResponseModel cancelAppointment(String appointmentId);

    AppointmentResponseModel completeAppointment(String appointmentId);


}
