package com.adeleke.clinicappointment.BusinessLayer.ClinicManagement;

import com.adeleke.clinicappointment.DataLayer.ClinicManagement.Clinic;
import com.adeleke.clinicappointment.DataLayer.ClinicManagement.ClinicIdentifier;
import com.adeleke.clinicappointment.DataLayer.ClinicManagement.ClinicRepository;
import com.adeleke.clinicappointment.Exception.exceptions.InvalidInputException;
import com.adeleke.clinicappointment.Exception.exceptions.NotFoundException;
import com.adeleke.clinicappointment.PresentationLayer.ClinicManagement.ClinicRequestModel;
import com.adeleke.clinicappointment.PresentationLayer.ClinicManagement.ClinicResponseModel;
import com.adeleke.clinicappointment.mappingLayer.ClinicManagement.ClinicRequestMapper;
import com.adeleke.clinicappointment.mappingLayer.ClinicManagement.ClinicResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepository clinicRepository;

    private final ClinicRequestMapper clinicRequestMapper;

    private final ClinicResponseMapper clinicResponseMapper;

    public ClinicServiceImpl(ClinicRepository clinicRepository, ClinicRequestMapper clinicRequestMapper, ClinicResponseMapper clinicResponseMapper) {
        this.clinicRepository = clinicRepository;
        this.clinicRequestMapper = clinicRequestMapper;
        this.clinicResponseMapper = clinicResponseMapper;
    }

    @Override
    public ClinicResponseModel createClinic(ClinicRequestModel requestModel) {
        if(requestModel == null) {
            throw new InvalidInputException("Clinic request model cannot be null");
        }

        Clinic clinicEntity = clinicRequestMapper.toEntity(requestModel, new ClinicIdentifier());
        Clinic savedClinic = clinicRepository.save(clinicEntity);
        return clinicResponseMapper.toClinicResponse(savedClinic);
    }

    @Override
    public ClinicResponseModel getClinicById(String clinicId) {
        Clinic clinic = clinicRepository.findByClinicId_ClinicId(clinicId);

        if (clinic == null) {
            throw new NotFoundException("Clinic with ID " + clinicId + " not found");
        }

        return clinicResponseMapper.toClinicResponse(clinic);
    }

    @Override
    public ClinicResponseModel updateClinic(String clinicId, ClinicRequestModel requestModel) {
        Clinic clinic = clinicRepository.findByClinicId_ClinicId(clinicId);
        if(clinic == null) {
            throw new NotFoundException("Clinic with ID " + clinicId + " not found");
        }
        if(requestModel == null) {
            throw new InvalidInputException("Clinic request model cannot be null");
        }

        clinic.setClinicName(requestModel.getClinicName());
        clinic.setClinicAddress(requestModel.getClinicAddress());

        Clinic updated = clinicRepository.save(clinic);
        return clinicResponseMapper.toClinicResponse(updated);
    }

    @Override
    public void deleteClinic(String clinicId) {
        Clinic clinic = clinicRepository.findByClinicId_ClinicId(clinicId);
        if (clinic == null) {
            throw new NotFoundException("Clinic with ID " + clinicId + " not found");
        }
        clinicRepository.delete(clinic);
    }
    @Override
    public List<ClinicResponseModel> getAllClinics() {
        List<Clinic> clinics = clinicRepository.findAll();
        return clinicResponseMapper.entityListToResponseModelList(clinics);
    }
}
