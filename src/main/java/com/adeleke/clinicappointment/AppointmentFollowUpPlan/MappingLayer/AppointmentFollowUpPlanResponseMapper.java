package com.adeleke.clinicappointment.AppointmentFollowUpPlan.MappingLayer;

import com.adeleke.clinicappointment.AppointmentFollowUpPlan.DataLayer.AppointmentFollowUpPlan;
import com.adeleke.clinicappointment.AppointmentFollowUpPlan.PresentationLayer.AppointmentFollowUpPlanResponseModel;
import com.adeleke.clinicappointment.AppointmentManagement.DataLayer.Appointment;
import com.adeleke.clinicappointment.ClinicManagement.DataLayer.Clinic;
import com.adeleke.clinicappointment.DoctorManagement.DataLayer.Doctor;
import com.adeleke.clinicappointment.PatientManagement.DataLayer.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentFollowUpPlanResponseMapper {

    @Mapping(target = "followUpPlanId", source = "followUpPlan.followUpPlanId.followUpPlanId")
    @Mapping(target = "recommendedFollowUpDate", source = "followUpPlan.recommendedFollowUpDate")
    @Mapping(target = "followUpReason", source = "followUpPlan.followUpReason")
    @Mapping(target = "status", source = "followUpPlan.status")

    @Mapping(target = "appointmentId", source = "appointment.appointmentId.appointmentId")
    @Mapping(target = "appointmentDateTime", source = "appointment.appointmentDateTime")
    @Mapping(target = "appointmentStatus", source = "appointment.appointmentStatus")

    @Mapping(target = "patientId", source = "patient.patientId.patientId")
    @Mapping(target = "patientFullName", source = "patient.patientFullName")
    @Mapping(target = "patientContactInfo", source = "patient.patientContactInfo")

    @Mapping(target = "doctorId", source = "doctor.doctorId.doctorId")
    @Mapping(target = "doctorFullName", source = "doctor.doctorFullName")
    @Mapping(target = "specialty", source = "doctor.specialty")

    @Mapping(target = "clinicId", source = "clinic.clinicId.clinicId")
    @Mapping(target = "clinicName", source = "clinic.clinicName")
    @Mapping(target = "clinicAddress", source = "clinic.clinicAddress")
    AppointmentFollowUpPlanResponseModel entityToResponseModel(
            AppointmentFollowUpPlan followUpPlan,
            Appointment appointment,
            Patient patient,
            Doctor doctor,
            Clinic clinic
    );
}