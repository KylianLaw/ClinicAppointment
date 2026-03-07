package com.adeleke.clinicappointment.AppointmentManagement.DataLayer;

import com.adeleke.clinicappointment.ClinicManagement.DataLayer.ClinicIdentifier;
import com.adeleke.clinicappointment.DoctorManagement.DataLayer.DoctorIdentifier;
import com.adeleke.clinicappointment.PatientManagement.DataLayer.PatientIdentifier;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "appointments")
    public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @Embedded
    private AppointmentIdentifier appointmentId;

    @Embedded
    private DoctorIdentifier doctorIdentifier;

    @Embedded
    private PatientIdentifier patientIdentifier;

    @Embedded
    private ClinicIdentifier clinicIdentifier;

    @Column(name = "appointment_date_time")
    private LocalDateTime appointmentDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_status")
    private AppointmentStatusEnum appointmentStatus;

    public Appointment(
            AppointmentIdentifier appointmentId,
            DoctorIdentifier doctorIdentifier,
            PatientIdentifier patientIdentifier,
            ClinicIdentifier clinicIdentifier,
            LocalDateTime appointmentDateTime
    ) {

        if (appointmentId == null)
            throw new IllegalArgumentException("appointmentId cannot be null");

        if (doctorIdentifier == null)
            throw new IllegalArgumentException("doctorIdentifier cannot be null");

        if (patientIdentifier == null)
            throw new IllegalArgumentException("patientIdentifier cannot be null");

        if (clinicIdentifier == null)
            throw new IllegalArgumentException("clinicIdentifier cannot be null");

        if (appointmentDateTime == null)
            throw new IllegalArgumentException("appointmentDateTime cannot be null");

        if (appointmentDateTime.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("Appointment cannot be scheduled in the past");

        this.appointmentId = appointmentId;
        this.doctorIdentifier = doctorIdentifier;
        this.patientIdentifier = patientIdentifier;
        this.clinicIdentifier = clinicIdentifier;
        this.appointmentDateTime = appointmentDateTime;
        this.appointmentStatus = AppointmentStatusEnum.SCHEDULED;
    }

    public void cancelAppointment() {

        if (this.appointmentStatus == AppointmentStatusEnum.COMPLETED)
            throw new IllegalStateException("Completed appointments cannot be cancelled");

        this.appointmentStatus = AppointmentStatusEnum.CANCELLED;
    }

    public void completeAppointment() {

        if (this.appointmentStatus == AppointmentStatusEnum.CANCELLED)
            throw new IllegalStateException("Cancelled appointments cannot be completed");

        this.appointmentStatus = AppointmentStatusEnum.COMPLETED;
    }

    public void updateAppointmentDetails(
            DoctorIdentifier doctorIdentifier,
            PatientIdentifier patientIdentifier,
            ClinicIdentifier clinicIdentifier,
            LocalDateTime appointmentDateTime
    ) {
        if (doctorIdentifier == null)
            throw new IllegalArgumentException("doctorIdentifier cannot be null");

        if (patientIdentifier == null)
            throw new IllegalArgumentException("patientIdentifier cannot be null");

        if (clinicIdentifier == null)
            throw new IllegalArgumentException("clinicIdentifier cannot be null");

        if (appointmentDateTime == null)
            throw new IllegalArgumentException("appointmentDateTime cannot be null");

        if (appointmentDateTime.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("Appointment cannot be scheduled in the past");

        this.doctorIdentifier = doctorIdentifier;
        this.patientIdentifier = patientIdentifier;
        this.clinicIdentifier = clinicIdentifier;
        this.appointmentDateTime = appointmentDateTime;
    }
}