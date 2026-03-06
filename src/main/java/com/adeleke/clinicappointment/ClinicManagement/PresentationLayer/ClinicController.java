package com.adeleke.clinicappointment.ClinicManagement.PresentationLayer;


import com.adeleke.clinicappointment.ClinicManagement.BusinessLayer.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<EntityModel<ClinicResponseModel>> createClinic(
            @RequestBody ClinicRequestModel request
    ) {
        ClinicResponseModel createdClinic = clinicService.createClinic(request);
        return new ResponseEntity<>(clinicAssembler.toModel(createdClinic), HttpStatus.CREATED);
    }

    @GetMapping("/{clinicId}")
    public ResponseEntity<EntityModel<ClinicResponseModel>> getClinic(
            @PathVariable String clinicId
    ) {
        ClinicResponseModel clinic = clinicService.getClinicById(clinicId);
        EntityModel<ClinicResponseModel> model = clinicAssembler.toModel(clinic);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<ClinicResponseModel>>> getAllClinics() {
        List<EntityModel<ClinicResponseModel>> clinics = clinicService.getAllClinics()
                .stream()
                .map(clinicAssembler::toModel)
                .collect(Collectors.toList());

        return new ResponseEntity<>(clinics, HttpStatus.OK);
    }

    @PutMapping("/{clinicId}")
    public ResponseEntity<EntityModel<ClinicResponseModel>> updateClinic(
            @PathVariable String clinicId,
            @RequestBody ClinicRequestModel requestModel
    ) {
        ClinicResponseModel updatedClinic = clinicService.updateClinic(clinicId, requestModel);
        return new ResponseEntity<>(clinicAssembler.toModel(updatedClinic), HttpStatus.OK);
    }

    @DeleteMapping("/{clinicId}")
    public ResponseEntity<Void> deleteClinic(
            @PathVariable String clinicId
    ) {
        clinicService.deleteClinic(clinicId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
