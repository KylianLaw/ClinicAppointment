package com.adeleke.clinicappointment.AppointmentManagement.PresentationLayer;


import com.adeleke.clinicappointment.AppointmentManagement.BusinessLayer.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentAssembler appointmentAssembler;

    @Autowired
    public AppointmentController(AppointmentService appointmentService,
                                 AppointmentAssembler appointmentAssembler) {
        this.appointmentService = appointmentService;
        this.appointmentAssembler = appointmentAssembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<AppointmentResponseModel>> createAppointment(
            @RequestBody AppointmentRequestModel request
    ) {
        AppointmentResponseModel createdAppointment = appointmentService.createAppointment(request);
        return new ResponseEntity<>(appointmentAssembler.toModel(createdAppointment), HttpStatus.CREATED);
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<EntityModel<AppointmentResponseModel>> getAppointment(
            @PathVariable String appointmentId
    ) {
        AppointmentResponseModel appointment = appointmentService.getAppointmentById(appointmentId);
        EntityModel<AppointmentResponseModel> model = appointmentAssembler.toModel(appointment);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<AppointmentResponseModel>>> getAllAppointments() {
        List<EntityModel<AppointmentResponseModel>> appointments = appointmentService.getAllAppointments()
                .stream()
                .map(appointmentAssembler::toModel)
                .collect(Collectors.toList());

        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<EntityModel<AppointmentResponseModel>> updateAppointment(
            @PathVariable String appointmentId,
            @RequestBody AppointmentRequestModel requestModel
    ) {
        AppointmentResponseModel updatedAppointment =
                appointmentService.updateAppointment(appointmentId, requestModel);

        return new ResponseEntity<>(appointmentAssembler.toModel(updatedAppointment), HttpStatus.OK);
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(
            @PathVariable String appointmentId
    ) {
        appointmentService.deleteAppointment(appointmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{appointmentId}/cancel")
    public ResponseEntity<EntityModel<AppointmentResponseModel>> cancelAppointment(
            @PathVariable String appointmentId
    ) {
        AppointmentResponseModel cancelledAppointment =
                appointmentService.cancelAppointment(appointmentId);

        return new ResponseEntity<>(appointmentAssembler.toModel(cancelledAppointment), HttpStatus.OK);
    }

    @PutMapping("/{appointmentId}/complete")
    public ResponseEntity<EntityModel<AppointmentResponseModel>> completeAppointment(
            @PathVariable String appointmentId
    ) {
        AppointmentResponseModel completedAppointment =
                appointmentService.completeAppointment(appointmentId);

        return new ResponseEntity<>(appointmentAssembler.toModel(completedAppointment), HttpStatus.OK);
    }
}