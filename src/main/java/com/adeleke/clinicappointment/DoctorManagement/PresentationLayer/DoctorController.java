package com.adeleke.clinicappointment.DoctorManagement.PresentationLayer;

import com.adeleke.clinicappointment.DoctorManagement.BusinessLayer.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorAssembler doctorAssembler;

    @Autowired
    public DoctorController(DoctorService doctorService, DoctorAssembler doctorAssembler) {
        this.doctorService = doctorService;
        this.doctorAssembler = doctorAssembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<DoctorResponseModel>> createDoctor(
            @RequestBody DoctorRequestModel request
    ) {
        DoctorResponseModel createdDoctor = doctorService.createDoctor(request);
        return new ResponseEntity<>(doctorAssembler.toModel(createdDoctor), HttpStatus.CREATED);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<EntityModel<DoctorResponseModel>> getDoctorById(
            @PathVariable String doctorId
    ) {
        DoctorResponseModel doctor = doctorService.getDoctorById(doctorId);
        EntityModel<DoctorResponseModel> model = doctorAssembler.toModel(doctor);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<DoctorResponseModel>>> getAllDoctors() {
        List<EntityModel<DoctorResponseModel>> doctors = doctorService.getAllDoctors()
                .stream()
                .map(doctorAssembler::toModel)
                .collect(Collectors.toList());

        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<EntityModel<DoctorResponseModel>> updateDoctor(
            @PathVariable String doctorId,
            @RequestBody DoctorRequestModel requestModel
    ) {
        DoctorResponseModel updatedDoctor = doctorService.updateDoctor(doctorId, requestModel);
        return new ResponseEntity<>(doctorAssembler.toModel(updatedDoctor), HttpStatus.OK);
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<Void> deleteDoctor(
            @PathVariable String doctorId
    ) {
        doctorService.deleteDoctor(doctorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}