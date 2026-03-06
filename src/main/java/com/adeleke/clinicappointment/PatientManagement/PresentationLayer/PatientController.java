package com.adeleke.clinicappointment.PatientManagement.PresentationLayer;

import com.adeleke.clinicappointment.PatientManagement.BusinessLayer.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/patients")
public class PatientController {

    private final PatientService patientService;
    private final PatientAssembler patientAssembler;

    @Autowired
    public PatientController(PatientService patientService, PatientAssembler patientAssembler) {
        this.patientService = patientService;
        this.patientAssembler = patientAssembler;
    }

    // Create a patient
    @PostMapping
    public ResponseEntity<EntityModel<PatientResponseModel>> createPatient(
            @RequestBody PatientRequestModel request
    ) {
        PatientResponseModel createdPatient = patientService.createPatient(request);
        return new ResponseEntity<>(patientAssembler.toModel(createdPatient), HttpStatus.CREATED);
    }

    // Get by ID
    @GetMapping("/{patientId}")
    public ResponseEntity<EntityModel<PatientResponseModel>> getPatientById(
            @PathVariable String patientId
    ) {
        PatientResponseModel patient = patientService.getPatientById(patientId);
        EntityModel<PatientResponseModel> model = patientAssembler.toModel(patient);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<PatientResponseModel>>> getAllPatients() {
        List<EntityModel<PatientResponseModel>> patients = patientService.getAllPatients()
                .stream()
                .map(patientAssembler::toModel)
                .collect(Collectors.toList());

        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<EntityModel<PatientResponseModel>> updatePatient(
            @PathVariable String patientId,
            @RequestBody PatientRequestModel request
    ) {
        PatientResponseModel updatedPatient = patientService.updatePatient(patientId, request);
        return new ResponseEntity<>(patientAssembler.toModel(updatedPatient), HttpStatus.OK);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> deletePatient(
            @PathVariable String patientId
    ) {
        patientService.deletePatient(patientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}