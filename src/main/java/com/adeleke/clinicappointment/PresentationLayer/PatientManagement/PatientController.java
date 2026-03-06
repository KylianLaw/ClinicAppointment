package com.adeleke.clinicappointment.PresentationLayer.PatientManagement;

import com.adeleke.clinicappointment.BusinessLayer.PatientManagement.PatientService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //Create a patient
    @PostMapping
    public ResponseEntity<PatientResponseModel> createPatient(
            @RequestBody PatientRequestModel request
    ) {
        return new ResponseEntity<>(patientService.createPatient(request), HttpStatus.CREATED);
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
    public ResponseEntity<List<PatientResponseModel>> getAllPatients() {
        return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.OK);
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientResponseModel> updatePatient(
            @PathVariable String patientId,
            @RequestBody PatientRequestModel request
    ) {
        return new ResponseEntity<>(patientService.updatePatient(patientId,request ), HttpStatus.OK );
}

    @DeleteMapping("/{patientId}")
    public ResponseEntity<PatientResponseModel> deletePatient(
            @PathVariable String patientId
    ) {
        patientService.deletePatient(patientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}