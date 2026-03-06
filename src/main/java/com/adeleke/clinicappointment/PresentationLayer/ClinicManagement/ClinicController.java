package com.adeleke.clinicappointment.PresentationLayer.ClinicManagement;


import com.adeleke.clinicappointment.BusinessLayer.ClinicManagement.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clinics")
public class ClinicController {

    private final ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @PostMapping
    public ClinicResponseModel createClinic( @RequestBody
            ClinicRequestModel request
    ) {
        return clinicService.createClinic(request);
    }

    @GetMapping("/{clinicId}")
    public ClinicResponseModel getClinic(@PathVariable String clinicId) {
        return clinicService.getClinicById(clinicId);
    }

    @GetMapping
    public List<ClinicResponseModel> getAllClinics() {
        return clinicService.getAllClinics();
    }

    @PutMapping("/{clinicId}")
    public ClinicResponseModel updateClinic(
            @PathVariable String clinicId,
            @RequestBody ClinicRequestModel requestModel
    ) {
        return clinicService.updateClinic(clinicId, requestModel);
    }

    @DeleteMapping("/{clinicId}")
    public void deleteClinic(
            @PathVariable String clinicId
    ) {
        clinicService.deleteClinic(clinicId);
    }



}
