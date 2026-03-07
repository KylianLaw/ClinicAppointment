package com.adeleke.clinicappointment.AppointmentManagement.PresentationLayer;

import com.adeleke.clinicappointment.AppointmentManagement.DataLayer.AppointmentStatusEnum;
import com.adeleke.clinicappointment.ClinicManagement.DataLayer.ClinicAddress;
import com.adeleke.clinicappointment.DoctorManagement.DataLayer.SpecialtyEnum;
import com.adeleke.clinicappointment.PatientManagement.DataLayer.PatientContactInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseModel extends RepresentationModel<AppointmentResponseModel> {


    // Appointment information (3 fields)
    String appointmentId;
    LocalDateTime appointmentDateTime;
    AppointmentStatusEnum appointmentStatus;

    // Patient information (3 fields)
    String patientId;
    String patientFullName;
    PatientContactInfo patientContactInfo ;

    // Doctor information (3 fields)
    String doctorId;
    String doctorFullName;
    SpecialtyEnum specialty;

    // Clinic information (3 fields)
    String clinicId;
    String clinicName;
    ClinicAddress clinicAddress;

}