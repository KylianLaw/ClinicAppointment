package com.adeleke.clinicappointment.DoctorManagement.BusinessLayer;

import com.adeleke.clinicappointment.DoctorManagement.DataLayer.Doctor;
import com.adeleke.clinicappointment.DoctorManagement.DataLayer.DoctorIdentifier;
import com.adeleke.clinicappointment.DoctorManagement.DataLayer.DoctorRepository;
import com.adeleke.clinicappointment.Exception.exceptions.InvalidInputException;
import com.adeleke.clinicappointment.Exception.exceptions.NotFoundException;
import com.adeleke.clinicappointment.DoctorManagement.PresentationLayer.DoctorRequestModel;
import com.adeleke.clinicappointment.DoctorManagement.PresentationLayer.DoctorResponseModel;
import com.adeleke.clinicappointment.DoctorManagement.MappingLayer.DoctorRequestMapper;
import com.adeleke.clinicappointment.DoctorManagement.MappingLayer.DoctorResponseMapper;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    private final DoctorRequestMapper requestMapper;

    private final DoctorResponseMapper responseMapper;

    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorRequestMapper requestMapper, DoctorResponseMapper responseMapper) {
        this.doctorRepository = doctorRepository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Override
    public DoctorResponseModel createDoctor(DoctorRequestModel requestModel) {
        if(requestModel == null) {
            throw new InvalidInputException("Doctor request model cannot be null");
        }

        Doctor doctorEntity = requestMapper.toEntity(requestModel, new DoctorIdentifier());
        Doctor savedDoctor = doctorRepository.save(doctorEntity);
        return responseMapper.toDoctorResponse(savedDoctor);
    }

    @Override
    public DoctorResponseModel getDoctorById(String doctorId) {
        if(doctorId == null){
            throw new InvalidInputException("Doctor Id cannot be null");
        }

        Doctor doctor = doctorRepository.findByDoctorId_DoctorId(doctorId);
        if (doctor == null) {
            throw new NotFoundException("Doctor with ID " + doctorId + " not found");
        }
        return responseMapper.toDoctorResponse(doctor);
    }

    @Override
    public java.util.List<DoctorResponseModel> getAllDoctors() {
        java.util.List<Doctor> doctors = doctorRepository.findAll();
        return responseMapper.entityListToResponseModelList(doctors);
    }

    @Override
    public DoctorResponseModel updateDoctor(String doctorId, DoctorRequestModel requestModel) {
        if(doctorId == null){
            throw new InvalidInputException("Doctor Id cannot be null");
        }
        Doctor doctor = doctorRepository.findByDoctorId_DoctorId(doctorId);

        if (doctor == null) {
            throw new NotFoundException("Doctor with ID " + doctorId + " not found");
        }

        if( requestModel == null) {
            throw new InvalidInputException("Doctor request model cannot be null");
        }
        doctor.setDoctorFullName(requestModel.getDoctorFullName());
        doctor.setSpecialty(requestModel.getSpecialty());

        Doctor updated = doctorRepository.save(doctor);
        return responseMapper.toDoctorResponse(updated);
    }

    @Override
    public void deleteDoctor(String doctorId) {
        if(doctorId == null){
            throw new InvalidInputException("Doctor Id cannot be null");
        }
        Doctor doctor = doctorRepository.findByDoctorId_DoctorId(doctorId);

        if (doctor == null) {
            throw new NotFoundException("Doctor with ID " + doctorId + " not found");
        }


        doctorRepository.delete(doctor);
    }

}
