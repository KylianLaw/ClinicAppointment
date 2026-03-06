package com.adeleke.clinicappointment.ClinicManagement.BusinessLayer;

import com.adeleke.clinicappointment.ClinicManagement.DataLayer.Clinic;
import com.adeleke.clinicappointment.ClinicManagement.DataLayer.ClinicAddress;
import com.adeleke.clinicappointment.ClinicManagement.DataLayer.ClinicIdentifier;
import com.adeleke.clinicappointment.ClinicManagement.DataLayer.ClinicRepository;
import com.adeleke.clinicappointment.Exception.exceptions.DuplicateClinicException;
import com.adeleke.clinicappointment.Exception.exceptions.InvalidInputException;
import com.adeleke.clinicappointment.Exception.exceptions.NotFoundException;
import com.adeleke.clinicappointment.ClinicManagement.PresentationLayer.ClinicRequestModel;
import com.adeleke.clinicappointment.ClinicManagement.PresentationLayer.ClinicResponseModel;
import com.adeleke.clinicappointment.ClinicManagement.MappingLayer.ClinicRequestMapper;
import com.adeleke.clinicappointment.ClinicManagement.MappingLayer.ClinicResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepository clinicRepository;
    private final ClinicRequestMapper clinicRequestMapper;
    private final ClinicResponseMapper clinicResponseMapper;

    public ClinicServiceImpl(ClinicRepository clinicRepository,
                             ClinicRequestMapper clinicRequestMapper,
                             ClinicResponseMapper clinicResponseMapper) {
        this.clinicRepository = clinicRepository;
        this.clinicRequestMapper = clinicRequestMapper;
        this.clinicResponseMapper = clinicResponseMapper;
    }

    @Override
    public ClinicResponseModel createClinic(ClinicRequestModel requestModel) {
        if (requestModel == null) {
            throw new InvalidInputException("Clinic request model cannot be null");
        }

        if (requestModel.getClinicName() == null || requestModel.getClinicName().isBlank()) {
            throw new InvalidInputException("Clinic name cannot be null or empty");
        }

        if (requestModel.getClinicAddress() == null) {
            throw new InvalidInputException("Clinic address cannot be null");
        }

        if (requestModel.getClinicAddress().getStreet() == null || requestModel.getClinicAddress().getStreet().isBlank()) {
            throw new InvalidInputException("Clinic street cannot be null or empty");
        }

        if (requestModel.getClinicAddress().getCity() == null || requestModel.getClinicAddress().getCity().isBlank()) {
            throw new InvalidInputException("Clinic city cannot be null or empty");
        }

        if (requestModel.getClinicAddress().getPostalCode() == null || requestModel.getClinicAddress().getPostalCode().isBlank()) {
            throw new InvalidInputException("Clinic postal code cannot be null or empty");
        }

        Clinic existingClinic = clinicRepository
                .findByClinicNameAndClinicAddress_StreetAndClinicAddress_CityAndClinicAddress_PostalCode(
                        requestModel.getClinicName(),
                        requestModel.getClinicAddress().getStreet(),
                        requestModel.getClinicAddress().getCity(),
                        requestModel.getClinicAddress().getPostalCode()
                );

        if (existingClinic != null) {
            throw new DuplicateClinicException("Clinic already exists with the same name and address");
        }

        Clinic clinicEntity = new Clinic(
                new ClinicIdentifier(),
                requestModel.getClinicName(),
                new ClinicAddress(
                        requestModel.getClinicAddress().getStreet(),
                        requestModel.getClinicAddress().getCity(),
                        requestModel.getClinicAddress().getPostalCode()
                )
        );

        Clinic savedClinic = clinicRepository.save(clinicEntity);
        return clinicResponseMapper.toClinicResponse(savedClinic);
    }

    @Override
    public ClinicResponseModel getClinicById(String clinicId) {
        if (clinicId == null || clinicId.isBlank()) {
            throw new InvalidInputException("Clinic id cannot be null or empty");
        }

        Clinic clinic = clinicRepository.findByClinicId_ClinicId(clinicId);

        if (clinic == null) {
            throw new NotFoundException("Clinic with ID " + clinicId + " not found");
        }

        return clinicResponseMapper.toClinicResponse(clinic);
    }

    @Override
    public ClinicResponseModel updateClinic(String clinicId, ClinicRequestModel requestModel) {
        if (clinicId == null || clinicId.isBlank()) {
            throw new InvalidInputException("Clinic id cannot be null or empty");
        }

        if (requestModel == null) {
            throw new InvalidInputException("Clinic request model cannot be null");
        }

        Clinic clinic = clinicRepository.findByClinicId_ClinicId(clinicId);
        if (clinic == null) {
            throw new NotFoundException("Clinic with ID " + clinicId + " not found");
        }

        if (requestModel.getClinicName() == null || requestModel.getClinicName().isBlank()) {
            throw new InvalidInputException("Clinic name cannot be null or empty");
        }

        if (requestModel.getClinicAddress() == null) {
            throw new InvalidInputException("Clinic address cannot be null");
        }

        if (requestModel.getClinicAddress().getStreet() == null || requestModel.getClinicAddress().getStreet().isBlank()) {
            throw new InvalidInputException("Clinic street cannot be null or empty");
        }

        if (requestModel.getClinicAddress().getCity() == null || requestModel.getClinicAddress().getCity().isBlank()) {
            throw new InvalidInputException("Clinic city cannot be null or empty");
        }

        if (requestModel.getClinicAddress().getPostalCode() == null || requestModel.getClinicAddress().getPostalCode().isBlank()) {
            throw new InvalidInputException("Clinic postal code cannot be null or empty");
        }

        Clinic existingClinic = clinicRepository
                .findByClinicNameAndClinicAddress_StreetAndClinicAddress_CityAndClinicAddress_PostalCode(
                        requestModel.getClinicName(),
                        requestModel.getClinicAddress().getStreet(),
                        requestModel.getClinicAddress().getCity(),
                        requestModel.getClinicAddress().getPostalCode()
                );

        if (existingClinic != null &&
                !existingClinic.getClinicId().getClinicId().equals(clinicId)) {
            throw new DuplicateClinicException("Another clinic already exists with the same name and address");
        }

        clinic.setClinicName(requestModel.getClinicName());
        clinic.setClinicAddress(new ClinicAddress(
                requestModel.getClinicAddress().getStreet(),
                requestModel.getClinicAddress().getCity(),
                requestModel.getClinicAddress().getPostalCode()
        ));

        Clinic updated = clinicRepository.save(clinic);
        return clinicResponseMapper.toClinicResponse(updated);
    }

    @Override
    public void deleteClinic(String clinicId) {
        if (clinicId == null || clinicId.isBlank()) {
            throw new InvalidInputException("Clinic id cannot be null or empty");
        }

        Clinic clinic = clinicRepository.findByClinicId_ClinicId(clinicId);
        if (clinic == null) {
            throw new NotFoundException("Clinic with ID " + clinicId + " not found");
        }

        /*
         * FUTURE BUSINESS RULE
         * A clinic cannot be deleted if linked to appointments.
         *
         * Example later:
         *
         * if (appointmentRepository.existsByClinicId(clinicId)) {
         *     throw new DuplicateClinicException(
         *         "Clinic cannot be deleted because there are existing appointments linked to this clinic"
         *     );
         * }
         */

        clinicRepository.delete(clinic);
    }

    @Override
    public List<ClinicResponseModel> getAllClinics() {
        List<Clinic> clinics = clinicRepository.findAll();
        return clinicResponseMapper.entityListToResponseModelList(clinics);
    }
}