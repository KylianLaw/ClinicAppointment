package com.adeleke.clinicappointment.AppointmentManagement.MappingLayer;

import com.adeleke.clinicappointment.AppointmentManagement.DataLayer.Appointment;
import com.adeleke.clinicappointment.AppointmentManagement.PresentationLayer.AppointmentResponseModel;
import com.adeleke.clinicappointment.ClinicManagement.DataLayer.Clinic;
import com.adeleke.clinicappointment.DoctorManagement.DataLayer.Doctor;
import com.adeleke.clinicappointment.PatientManagement.DataLayer.Patient;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AppointmentResponseMapper {

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
    AppointmentResponseModel entityToResponseModel(
            Appointment appointment,
            Patient patient,
            Doctor doctor,
            Clinic clinic
    );

}