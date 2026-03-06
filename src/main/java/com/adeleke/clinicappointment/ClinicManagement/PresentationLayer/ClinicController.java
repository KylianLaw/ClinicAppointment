package com.adeleke.clinicappointment.ClinicManagement.PresentationLayer;


import com.adeleke.clinicappointment.ClinicManagement.BusinessLayer.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clinics")
public class ClinicController {

    private final ClinicService clinicService;
    private final ClinicAssembler clinicAssembler;
    @Autowired
    public ClinicController(ClinicService clinicService, ClinicAssembler clinicAssembler) {
        this.clinicService = clinicService;
        this.clinicAssembler = clinicAssembler;
    }

    @PostMapping
    public ResponseEntity<ClinicResponseModel> createClinic(@RequestBody
            ClinicRequestModel request
    ) {
        return new ResponseEntity<>(clinicService.createClinic(request), HttpStatus.CREATED);
    }

    @GetMapping("/{clinicId}")
    public ResponseEntity<EntityModel<ClinicResponseModel>> getClinic(@PathVariable String clinicId) {
        ClinicResponseModel clinic = clinicService.getClinicById(clinicId);
        EntityModel<ClinicResponseModel> model = clinicAssembler.toModel(clinic);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClinicResponseModel>> getAllClinics() {
        return new ResponseEntity<>(clinicService.getAllClinics(), HttpStatus.OK);
    }

    @PutMapping("/{clinicId}")
    public ResponseEntity<ClinicResponseModel> updateClinic(
            @PathVariable String clinicId,
            @RequestBody ClinicRequestModel requestModel
    ) {
        return new ResponseEntity<>(clinicService.updateClinic(clinicId, requestModel), HttpStatus.OK);
    }

    @DeleteMapping("/{clinicId}")
    public ResponseEntity<ClinicResponseModel> deleteClinic(
            @PathVariable String clinicId
    ) {
        clinicService.deleteClinic(clinicId);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }



}
