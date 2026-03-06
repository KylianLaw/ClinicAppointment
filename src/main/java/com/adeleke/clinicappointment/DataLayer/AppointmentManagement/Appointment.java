package com.adeleke.clinicappointment.DataLayer.AppointmentManagement;

import com.adeleke.clinicappointment.DataLayer.ClinicManagement.ClinicIdentifier;
import com.adeleke.clinicappointment.DataLayer.DoctorManagement.DoctorIdentifier;
import com.adeleke.clinicappointment.DataLayer.PatientManegement.PatientIdentifier;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Appointment")
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

    public Appointment(AppointmentIdentifier appointmentId, DoctorIdentifier doctorIdentifier, PatientIdentifier patientIdentifier, ClinicIdentifier clinicIdentifier, LocalDateTime appointmentDateTime, AppointmentStatusEnum appointmentStatus) {

        if(appointmentId == null) throw new IllegalArgumentException("appointmentId cannot be null");
        if(doctorIdentifier == null) throw new IllegalArgumentException("doctorIdentifier cannot be null");
        if(patientIdentifier == null) throw new IllegalArgumentException("patientIdentifier cannot be null");
        if(clinicIdentifier == null) throw new IllegalArgumentException("clinicIdentifier cannot be null");
        if(appointmentDateTime == null) throw new IllegalArgumentException("appointmentDateTime cannot be null");



        this.appointmentId = appointmentId;
        this.doctorIdentifier = doctorIdentifier;
        this.patientIdentifier = patientIdentifier;
        this.clinicIdentifier = clinicIdentifier;
        this.appointmentDateTime = appointmentDateTime;
        this.appointmentStatus = AppointmentStatusEnum.SCHEDULED;
    }
    public void CancelAppointment() {

        if (this.appointmentStatus == AppointmentStatusEnum.COMPLETED) throw new IllegalArgumentException("Completed appointments cannot be cancelled");

        this.appointmentStatus = AppointmentStatusEnum.CANCELLED;
    }

    public void CompleteAppointment(AppointmentStatusEnum appointmentStatus) {

        if (this.appointmentStatus == AppointmentStatusEnum.CANCELLED) throw new IllegalArgumentException("Cancelled appointments cannot be completed");

        this.appointmentStatus = AppointmentStatusEnum.COMPLETED;
    }
    public void setAppointmentStatus(AppointmentStatusEnum appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }
}
