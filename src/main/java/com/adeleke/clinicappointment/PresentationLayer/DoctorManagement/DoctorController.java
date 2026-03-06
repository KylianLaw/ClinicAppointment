package com.adeleke.clinicappointment.PresentationLayer.DoctorManagement;

import com.adeleke.clinicappointment.BusinessLayer.DoctorManagement.DoctorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<DoctorResponseModel> createDoctor(
            @RequestBody DoctorRequestModel request
    ) {
        return new ResponseEntity<>(doctorService.createDoctor(request), HttpStatus.CREATED);
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
    public ResponseEntity<List<DoctorResponseModel>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorResponseModel> updateDoctor(
            @PathVariable String doctorId,
            @RequestBody DoctorRequestModel requestModel
    ) {
        return new ResponseEntity<>(doctorService.updateDoctor(doctorId, requestModel), HttpStatus.OK);
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<DoctorResponseModel> deleteDoctor(
            @PathVariable String doctorId
    ) {
        doctorService.deleteDoctor(doctorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
