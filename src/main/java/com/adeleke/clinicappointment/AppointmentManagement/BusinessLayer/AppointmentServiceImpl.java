package com.adeleke.clinicappointment.AppointmentManagement.BusinessLayer;

import com.adeleke.clinicappointment.AppointmentManagement.DataLayer.Appointment;
import com.adeleke.clinicappointment.AppointmentManagement.DataLayer.AppointmentIdentifier;
import com.adeleke.clinicappointment.AppointmentManagement.DataLayer.AppointmentRepository;
import com.adeleke.clinicappointment.AppointmentManagement.MappingLayer.AppointmentRequestMapper;
import com.adeleke.clinicappointment.AppointmentManagement.MappingLayer.AppointmentResponseMapper;
import com.adeleke.clinicappointment.AppointmentManagement.PresentationLayer.AppointmentRequestModel;
import com.adeleke.clinicappointment.AppointmentManagement.PresentationLayer.AppointmentResponseModel;
import com.adeleke.clinicappointment.ClinicManagement.BusinessLayer.ClinicService;
import com.adeleke.clinicappointment.ClinicManagement.DataLayer.ClinicIdentifier;
import com.adeleke.clinicappointment.ClinicManagement.PresentationLayer.ClinicResponseModel;
import com.adeleke.clinicappointment.DoctorManagement.BusinessLayer.DoctorService;
import com.adeleke.clinicappointment.DoctorManagement.DataLayer.DoctorIdentifier;
import com.adeleke.clinicappointment.DoctorManagement.PresentationLayer.DoctorResponseModel;
import com.adeleke.clinicappointment.Exception.exceptions.InvalidInputException;
import com.adeleke.clinicappointment.Exception.exceptions.NotFoundException;
import com.adeleke.clinicappointment.Exception.exceptions.OverlappingAppointmentException;
import com.adeleke.clinicappointment.PatientManagement.BusinessLayer.PatientService;
import com.adeleke.clinicappointment.PatientManagement.DataLayer.PatientIdentifier;
import com.adeleke.clinicappointment.PatientManagement.PresentationLayer.PatientResponseModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final AppointmentRequestMapper requestMapper;

    private final AppointmentResponseMapper responseMapper;

    private final PatientService patientService;

    private final DoctorService doctorService;

    private final ClinicService clinicService;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentRequestMapper requestMapper, AppointmentResponseMapper responseMapper, PatientService patientService, DoctorService doctorService, ClinicService clinicService) {
        this.appointmentRepository = appointmentRepository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.clinicService = clinicService;
    }

    @Override
    @Transactional
    public AppointmentResponseModel createAppointment(AppointmentRequestModel requestModel) {
        if (requestModel == null) {
            throw new InvalidInputException("Appointment request model cannot be null");
        }

        if (requestModel.getPatientId() == null || requestModel.getPatientId().isBlank()) {
            throw new InvalidInputException("Patient id cannot be null or blank");
        }

        if (requestModel.getDoctorId() == null || requestModel.getDoctorId().isBlank()) {
            throw new InvalidInputException("Doctor id cannot be null or blank");
        }

        if (requestModel.getClinicId() == null || requestModel.getClinicId().isBlank()) {
            throw new InvalidInputException("Clinic id cannot be null or blank");
        }

        if (requestModel.getAppointmentDateTime() == null) {
            throw new InvalidInputException("Appointment date and time cannot be null");
        }

        // 1. Validate related subdomains exist
        PatientResponseModel patient = patientService.getPatientById(requestModel.getPatientId());
        DoctorResponseModel doctor = doctorService.getDoctorById(requestModel.getDoctorId());
        ClinicResponseModel clinic = clinicService.getClinicById(requestModel.getClinicId());

        // 2. Check overlapping appointment for same patient at same date/time
        boolean exists = appointmentRepository
                .existsByPatientIdentifier_PatientIdAndAppointmentDateTime(
                        requestModel.getPatientId(),
                        requestModel.getAppointmentDateTime()
                );

        if (exists) {
            throw new OverlappingAppointmentException("Patient already has an appointment at this date and time");
        }

        // 3. Build identifiers
        AppointmentIdentifier appointmentIdentifier = new AppointmentIdentifier();
        DoctorIdentifier doctorIdentifier = new DoctorIdentifier(requestModel.getDoctorId());
        PatientIdentifier patientIdentifier = new PatientIdentifier(requestModel.getPatientId());
        ClinicIdentifier clinicIdentifier = new ClinicIdentifier(requestModel.getClinicId());

        // 4. Map request to entity
        Appointment appointmentEntity = requestMapper.toAppointment(
                requestModel,
                appointmentIdentifier,
                doctorIdentifier,
                patientIdentifier,
                clinicIdentifier
        );

        // 5. Save
        Appointment savedAppointment = appointmentRepository.save(appointmentEntity);

        // 6. Build response
        AppointmentResponseModel responseModel = new AppointmentResponseModel();
        responseModel.setAppointmentId(savedAppointment.getAppointmentId().getAppointmentId());
        responseModel.setAppointmentDateTime(savedAppointment.getAppointmentDateTime());
        responseModel.setAppointmentStatus(savedAppointment.getAppointmentStatus());

        responseModel.setPatientId(patient.getPatientId());
        responseModel.setPatientFullName(patient.getPatientFullName());
        responseModel.setPatientContactInfo(patient.getContactInfoResponse());

        responseModel.setDoctorId(doctor.getDoctorId());
        responseModel.setDoctorFullName(doctor.getDoctorFullName());
        responseModel.setSpecialty(doctor.getSpecialty());

        responseModel.setClinicId(clinic.getClinicId());
        responseModel.setClinicName(clinic.getClinicName());
        responseModel.setClinicAddress(clinic.getClinicAddress());

        return responseModel;
    }

    @Override
    @Transactional
    public List<AppointmentResponseModel> getAllAppointments() {

        List<Appointment> appointments = appointmentRepository.findAll();

        return appointments.stream().map(appointment -> {

            // Fetch related subdomains
            PatientResponseModel patient =
                    patientService.getPatientById(
                            appointment.getPatientIdentifier().getPatientId());

            DoctorResponseModel doctor =
                    doctorService.getDoctorById(
                            appointment.getDoctorIdentifier().getDoctorId());

            ClinicResponseModel clinic =
                    clinicService.getClinicById(
                            appointment.getClinicIdentifier().getClinicId());

            // Build response
            AppointmentResponseModel response = new AppointmentResponseModel();

            response.setAppointmentId(
                    appointment.getAppointmentId().getAppointmentId());

            response.setAppointmentDateTime(
                    appointment.getAppointmentDateTime());

            response.setAppointmentStatus(
                    appointment.getAppointmentStatus());

            response.setPatientId(patient.getPatientId());
            response.setPatientFullName(patient.getPatientFullName());
            response.setPatientContactInfo(patient.getContactInfoResponse());

            response.setDoctorId(doctor.getDoctorId());
            response.setDoctorFullName(doctor.getDoctorFullName());
            response.setSpecialty(doctor.getSpecialty());

            response.setClinicId(clinic.getClinicId());
            response.setClinicName(clinic.getClinicName());
            response.setClinicAddress(clinic.getClinicAddress());

            return response;

        }).toList();
    }


    @Override
    @Transactional
    public AppointmentResponseModel getAppointmentById(String appointmentId) {

        if (appointmentId == null || appointmentId.isBlank()) {
            throw new InvalidInputException("Appointment id cannot be null or blank");
        } else {
            Optional<Appointment> foundAppointment = appointmentRepository.findByAppointmentId_AppointmentId(appointmentId);

            if (foundAppointment.isEmpty()) {
                throw new NotFoundException("Appointment not found with id: " + appointmentId);
            } else {
                Appointment appointment = foundAppointment.get();

                PatientResponseModel patient =
                        patientService.getPatientById(appointment.getPatientIdentifier().getPatientId());

                DoctorResponseModel doctor =
                        doctorService.getDoctorById(appointment.getDoctorIdentifier().getDoctorId());

                ClinicResponseModel clinic =
                        clinicService.getClinicById(appointment.getClinicIdentifier().getClinicId());

                AppointmentResponseModel responseModel = new AppointmentResponseModel();

                responseModel.setAppointmentId(appointment.getAppointmentId().getAppointmentId());
                responseModel.setAppointmentDateTime(appointment.getAppointmentDateTime());
                responseModel.setAppointmentStatus(appointment.getAppointmentStatus());

                responseModel.setPatientId(patient.getPatientId());
                responseModel.setPatientFullName(patient.getPatientFullName());
                responseModel.setPatientContactInfo(patient.getContactInfoResponse());

                responseModel.setDoctorId(doctor.getDoctorId());
                responseModel.setDoctorFullName(doctor.getDoctorFullName());
                responseModel.setSpecialty(doctor.getSpecialty());

                responseModel.setClinicId(clinic.getClinicId());
                responseModel.setClinicName(clinic.getClinicName());
                responseModel.setClinicAddress(clinic.getClinicAddress());

                return responseModel;
            }
        }


    }


    @Override
    @Transactional
    public AppointmentResponseModel updateAppointment(String appointmentId, AppointmentRequestModel requestModel) {

        if (appointmentId == null || appointmentId.isBlank()) {
            throw new InvalidInputException("Appointment id cannot be null or blank");
        } else if (requestModel == null) {
            throw new InvalidInputException("Appointment request model cannot be null");
        } else {
            Optional<Appointment> foundAppointment = appointmentRepository.findByAppointmentId_AppointmentId(appointmentId);

            if (foundAppointment.isEmpty()) {
                throw new NotFoundException("Appointment not found with id: " + appointmentId);
            } else {
                Appointment existingAppointment = foundAppointment.get();

                PatientResponseModel patient = patientService.getPatientById(requestModel.getPatientId());
                DoctorResponseModel doctor = doctorService.getDoctorById(requestModel.getDoctorId());
                ClinicResponseModel clinic = clinicService.getClinicById(requestModel.getClinicId());

                boolean overlappingAppointmentExists =
                        appointmentRepository.existsByPatientIdentifier_PatientIdAndAppointmentDateTime(
                                requestModel.getPatientId(),
                                requestModel.getAppointmentDateTime()
                        );

                if (overlappingAppointmentExists
                        && !(existingAppointment.getPatientIdentifier().getPatientId().equals(requestModel.getPatientId())
                        && existingAppointment.getAppointmentDateTime().equals(requestModel.getAppointmentDateTime()))) {
                    throw new OverlappingAppointmentException(
                            "Patient already has an appointment scheduled at this date and time."
                    );
                } else {

                    existingAppointment.updateAppointmentDetails(
                            new DoctorIdentifier(requestModel.getDoctorId()),
                            new PatientIdentifier(requestModel.getPatientId()),
                            new ClinicIdentifier(requestModel.getClinicId()),
                            requestModel.getAppointmentDateTime()
                    );

                    Appointment savedAppointment = appointmentRepository.save(existingAppointment);

                    AppointmentResponseModel responseModel = new AppointmentResponseModel();

                    responseModel.setAppointmentId(savedAppointment.getAppointmentId().getAppointmentId());
                    responseModel.setAppointmentDateTime(savedAppointment.getAppointmentDateTime());
                    responseModel.setAppointmentStatus(savedAppointment.getAppointmentStatus());

                    responseModel.setPatientId(patient.getPatientId());
                    responseModel.setPatientFullName(patient.getPatientFullName());
                    responseModel.setPatientContactInfo(patient.getContactInfoResponse());

                    responseModel.setDoctorId(doctor.getDoctorId());
                    responseModel.setDoctorFullName(doctor.getDoctorFullName());
                    responseModel.setSpecialty(doctor.getSpecialty());

                    responseModel.setClinicId(clinic.getClinicId());
                    responseModel.setClinicName(clinic.getClinicName());
                    responseModel.setClinicAddress(clinic.getClinicAddress());

                    return responseModel;
                }
            }
        }
    }

    @Override
    @Transactional
    public void deleteAppointment(String appointmentId) {

        if (appointmentId == null || appointmentId.isBlank()) {
            throw new InvalidInputException("Appointment id cannot be null or blank");
        } else {
            Optional<Appointment> foundAppointment = appointmentRepository.findByAppointmentId_AppointmentId(appointmentId);

            if (foundAppointment.isEmpty()) {
                throw new NotFoundException("Appointment not found with id: " + appointmentId);
            } else {
                Appointment appointment = foundAppointment.get();
                appointmentRepository.delete(appointment);
            }
        }
    }

    @Override
    @Transactional
    public AppointmentResponseModel cancelAppointment(String appointmentId) {

        if (appointmentId == null || appointmentId.isBlank()) {
            throw new InvalidInputException("Appointment id cannot be null or blank");
        } else {
            Optional<Appointment> foundAppointment = appointmentRepository.findByAppointmentId_AppointmentId(appointmentId);

            if (foundAppointment.isEmpty()) {
                throw new NotFoundException("Appointment not found with id: " + appointmentId);
            } else {
                Appointment appointment = foundAppointment.get();
                appointment.cancelAppointment();

                Appointment savedAppointment = appointmentRepository.save(appointment);

                PatientResponseModel patient =
                        patientService.getPatientById(savedAppointment.getPatientIdentifier().getPatientId());

                DoctorResponseModel doctor =
                        doctorService.getDoctorById(savedAppointment.getDoctorIdentifier().getDoctorId());

                ClinicResponseModel clinic =
                        clinicService.getClinicById(savedAppointment.getClinicIdentifier().getClinicId());

                AppointmentResponseModel responseModel = new AppointmentResponseModel();

                responseModel.setAppointmentId(savedAppointment.getAppointmentId().getAppointmentId());
                responseModel.setAppointmentDateTime(savedAppointment.getAppointmentDateTime());
                responseModel.setAppointmentStatus(savedAppointment.getAppointmentStatus());

                responseModel.setPatientId(patient.getPatientId());
                responseModel.setPatientFullName(patient.getPatientFullName());
                responseModel.setPatientContactInfo(patient.getContactInfoResponse());

                responseModel.setDoctorId(doctor.getDoctorId());
                responseModel.setDoctorFullName(doctor.getDoctorFullName());
                responseModel.setSpecialty(doctor.getSpecialty());

                responseModel.setClinicId(clinic.getClinicId());
                responseModel.setClinicName(clinic.getClinicName());
                responseModel.setClinicAddress(clinic.getClinicAddress());

                return responseModel;
            }
        }
    }

    @Override
    @Transactional
    public AppointmentResponseModel completeAppointment(String appointmentId) {

        if (appointmentId == null || appointmentId.isBlank()) {
            throw new InvalidInputException("Appointment id cannot be null or blank");
        } else {
            Optional<Appointment> foundAppointment = appointmentRepository.findByAppointmentId_AppointmentId(appointmentId);

            if (foundAppointment.isEmpty()) {
                throw new NotFoundException("Appointment not found with id: " + appointmentId);
            } else {
                Appointment appointment = foundAppointment.get();
                appointment.completeAppointment();

                Appointment savedAppointment = appointmentRepository.save(appointment);

                PatientResponseModel patient =
                        patientService.getPatientById(savedAppointment.getPatientIdentifier().getPatientId());

                DoctorResponseModel doctor =
                        doctorService.getDoctorById(savedAppointment.getDoctorIdentifier().getDoctorId());

                ClinicResponseModel clinic =
                        clinicService.getClinicById(savedAppointment.getClinicIdentifier().getClinicId());

                AppointmentResponseModel responseModel = new AppointmentResponseModel();

                responseModel.setAppointmentId(savedAppointment.getAppointmentId().getAppointmentId());
                responseModel.setAppointmentDateTime(savedAppointment.getAppointmentDateTime());
                responseModel.setAppointmentStatus(savedAppointment.getAppointmentStatus());

                responseModel.setPatientId(patient.getPatientId());
                responseModel.setPatientFullName(patient.getPatientFullName());
                responseModel.setPatientContactInfo(patient.getContactInfoResponse());

                responseModel.setDoctorId(doctor.getDoctorId());
                responseModel.setDoctorFullName(doctor.getDoctorFullName());
                responseModel.setSpecialty(doctor.getSpecialty());

                responseModel.setClinicId(clinic.getClinicId());
                responseModel.setClinicName(clinic.getClinicName());
                responseModel.setClinicAddress(clinic.getClinicAddress());

                return responseModel;
            }
        }
    }







}
