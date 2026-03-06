package com.adeleke.clinicappointment.AppointmentManagement.DataLayer;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class AppointmentIdentifier {
    @Column(name = "appointment_id", nullable = false)
    private String appointmentId;

    public AppointmentIdentifier (){
        this.appointmentId = "Appointment-" + java.util.UUID.randomUUID().toString();
    }

    public AppointmentIdentifier (String appointmentId){
        this.appointmentId =  appointmentId;
    }


}
