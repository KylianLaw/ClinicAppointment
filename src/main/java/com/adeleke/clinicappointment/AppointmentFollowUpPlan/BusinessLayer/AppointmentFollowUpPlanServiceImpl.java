package com.adeleke.clinicappointment.AppointmentFollowUpPlan.BusinessLayer;

import com.adeleke.clinicappointment.AppointmentFollowUpPlan.DataLayer.AppointmentFollowUpPlan;
import com.adeleke.clinicappointment.AppointmentFollowUpPlan.DataLayer.AppointmentFollowUpPlanIdentifier;
import com.adeleke.clinicappointment.AppointmentFollowUpPlan.DataLayer.AppointmentFollowUpPlanRepository;
import com.adeleke.clinicappointment.AppointmentFollowUpPlan.DataLayer.FollowUpPlanStatusEnum;
import com.adeleke.clinicappointment.AppointmentFollowUpPlan.MappingLayer.AppointmentFollowUpPlanRequestMapper;
import com.adeleke.clinicappointment.AppointmentFollowUpPlan.PresentationLayer.AppointmentFollowUpPlanRequestModel;
import com.adeleke.clinicappointment.AppointmentFollowUpPlan.PresentationLayer.AppointmentFollowUpPlanResponseModel;
import com.adeleke.clinicappointment.AppointmentManagement.BusinessLayer.AppointmentService;
import com.adeleke.clinicappointment.AppointmentManagement.DataLayer.AppointmentIdentifier;
import com.adeleke.clinicappointment.AppointmentManagement.PresentationLayer.AppointmentResponseModel;
import com.adeleke.clinicappointment.ClinicManagement.BusinessLayer.ClinicService;
import com.adeleke.clinicappointment.ClinicManagement.DataLayer.ClinicIdentifier;
import com.adeleke.clinicappointment.ClinicManagement.PresentationLayer.ClinicResponseModel;
import com.adeleke.clinicappointment.DoctorManagement.BusinessLayer.DoctorService;
import com.adeleke.clinicappointment.DoctorManagement.DataLayer.DoctorIdentifier;
import com.adeleke.clinicappointment.DoctorManagement.PresentationLayer.DoctorResponseModel;
import com.adeleke.clinicappointment.Exception.exceptions.InvalidInputException;
import com.adeleke.clinicappointment.Exception.exceptions.NotFoundException;
import com.adeleke.clinicappointment.PatientManagement.BusinessLayer.PatientService;
import com.adeleke.clinicappointment.PatientManagement.DataLayer.PatientIdentifier;
import com.adeleke.clinicappointment.PatientManagement.PresentationLayer.PatientResponseModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentFollowUpPlanServiceImpl implements AppointmentFollowUpPlanService {

    private final AppointmentFollowUpPlanRepository appointmentFollowUpPlanRepository;
    private final AppointmentFollowUpPlanRequestMapper requestMapper;
    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final ClinicService clinicService;

    public AppointmentFollowUpPlanServiceImpl(
            AppointmentFollowUpPlanRepository appointmentFollowUpPlanRepository,
            AppointmentFollowUpPlanRequestMapper requestMapper,
            AppointmentService appointmentService,
            PatientService patientService,
            DoctorService doctorService,
            ClinicService clinicService
    ) {
        this.appointmentFollowUpPlanRepository = appointmentFollowUpPlanRepository;
        this.requestMapper = requestMapper;
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.clinicService = clinicService;
    }

    @Override
    @Transactional
    public List<AppointmentFollowUpPlanResponseModel> getAllFollowUpPlans() {

        List<AppointmentFollowUpPlan> followUpPlans = appointmentFollowUpPlanRepository.findAll();

        return followUpPlans.stream().map(followUpPlan -> {

            AppointmentResponseModel appointment =
                    appointmentService.getAppointmentById(
                            followUpPlan.getAppointmentId().getAppointmentId()
                    );

            PatientResponseModel patient =
                    patientService.getPatientById(
                            followUpPlan.getPatientId().getPatientId()
                    );

            DoctorResponseModel doctor =
                    doctorService.getDoctorById(
                            followUpPlan.getDoctorId().getDoctorId()
                    );

            ClinicResponseModel clinic =
                    clinicService.getClinicById(
                            followUpPlan.getClinicId().getClinicId()
                    );

            AppointmentFollowUpPlanResponseModel responseModel = new AppointmentFollowUpPlanResponseModel();

            responseModel.setFollowUpPlanId(followUpPlan.getFollowUpPlanId().getFollowUpPlanId());
            responseModel.setRecommendedFollowUpDate(followUpPlan.getRecommendedFollowUpDate());
            responseModel.setFollowUpReason(followUpPlan.getFollowUpReason());
            responseModel.setStatus(followUpPlan.getStatus());

            responseModel.setAppointmentId(appointment.getAppointmentId());
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
        }).toList();
    }

    @Override
    @Transactional
    public AppointmentFollowUpPlanResponseModel getFollowUpPlanById(String followUpPlanId) {

        if (followUpPlanId == null || followUpPlanId.isBlank()) {
            throw new InvalidInputException("Follow-up plan id cannot be null or blank");
        }

        Optional<AppointmentFollowUpPlan> foundFollowUpPlan =
                appointmentFollowUpPlanRepository.findByFollowUpPlanId_FollowUpPlanId(followUpPlanId);

        if (foundFollowUpPlan.isEmpty()) {
            throw new NotFoundException("Follow-up plan not found with id: " + followUpPlanId);
        }

        AppointmentFollowUpPlan followUpPlan = foundFollowUpPlan.get();

        AppointmentResponseModel appointment =
                appointmentService.getAppointmentById(followUpPlan.getAppointmentId().getAppointmentId());

        PatientResponseModel patient =
                patientService.getPatientById(followUpPlan.getPatientId().getPatientId());

        DoctorResponseModel doctor =
                doctorService.getDoctorById(followUpPlan.getDoctorId().getDoctorId());

        ClinicResponseModel clinic =
                clinicService.getClinicById(followUpPlan.getClinicId().getClinicId());

        AppointmentFollowUpPlanResponseModel responseModel = new AppointmentFollowUpPlanResponseModel();

        responseModel.setFollowUpPlanId(followUpPlan.getFollowUpPlanId().getFollowUpPlanId());
        responseModel.setRecommendedFollowUpDate(followUpPlan.getRecommendedFollowUpDate());
        responseModel.setFollowUpReason(followUpPlan.getFollowUpReason());
        responseModel.setStatus(followUpPlan.getStatus());

        responseModel.setAppointmentId(appointment.getAppointmentId());
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

    @Override
    @Transactional
    public AppointmentFollowUpPlanResponseModel createFollowUpPlan(AppointmentFollowUpPlanRequestModel requestModel) {

        if (requestModel == null) {
            throw new InvalidInputException("Follow-up plan request model cannot be null");
        }

        if (requestModel.getAppointmentId() == null || requestModel.getAppointmentId().isBlank()) {
            throw new InvalidInputException("Appointment id cannot be null or blank");
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

        if (requestModel.getRecommendedFollowUpDate() == null) {
            throw new InvalidInputException("Recommended follow-up date cannot be null");
        }

        if (requestModel.getFollowUpReason() == null || requestModel.getFollowUpReason().isBlank()) {
            throw new InvalidInputException("Follow-up reason cannot be null or blank");
        }

        // Validate referenced appointment exists
        AppointmentResponseModel appointment =
                appointmentService.getAppointmentById(requestModel.getAppointmentId());

        // Validate related subdomains exist
        PatientResponseModel patient =
                patientService.getPatientById(requestModel.getPatientId());

        DoctorResponseModel doctor =
                doctorService.getDoctorById(requestModel.getDoctorId());

        ClinicResponseModel clinic =
                clinicService.getClinicById(requestModel.getClinicId());

        // Invariant: recommendedFollowUpDate must be after original appointmentDateTime
        if (!requestModel.getRecommendedFollowUpDate().isAfter(appointment.getAppointmentDateTime())) {
            throw new InvalidInputException(
                    "Recommended follow-up date must be after the original appointment date and time"
            );
        }

        AppointmentFollowUpPlanIdentifier followUpPlanIdentifier = new AppointmentFollowUpPlanIdentifier();
        AppointmentIdentifier appointmentIdentifier = new AppointmentIdentifier(requestModel.getAppointmentId());
        PatientIdentifier patientIdentifier = new PatientIdentifier(requestModel.getPatientId());
        DoctorIdentifier doctorIdentifier = new DoctorIdentifier(requestModel.getDoctorId());
        ClinicIdentifier clinicIdentifier = new ClinicIdentifier(requestModel.getClinicId());

        AppointmentFollowUpPlan followUpPlan = requestMapper.toAppointmentFollowUpPlan(
                requestModel,
                followUpPlanIdentifier,
                patientIdentifier,
                appointmentIdentifier,
                doctorIdentifier,
                clinicIdentifier
        );

        followUpPlan.setStatus(FollowUpPlanStatusEnum.PLANNED);

        AppointmentFollowUpPlan savedFollowUpPlan = appointmentFollowUpPlanRepository.save(followUpPlan);

        AppointmentFollowUpPlanResponseModel responseModel = new AppointmentFollowUpPlanResponseModel();

        responseModel.setFollowUpPlanId(savedFollowUpPlan.getFollowUpPlanId().getFollowUpPlanId());
        responseModel.setRecommendedFollowUpDate(savedFollowUpPlan.getRecommendedFollowUpDate());
        responseModel.setFollowUpReason(savedFollowUpPlan.getFollowUpReason());
        responseModel.setStatus(savedFollowUpPlan.getStatus());

        responseModel.setAppointmentId(appointment.getAppointmentId());
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

    @Override
    @Transactional
    public AppointmentFollowUpPlanResponseModel updateFollowUpPlan(
            String followUpPlanId,
            AppointmentFollowUpPlanRequestModel requestModel
    ) {

        if (followUpPlanId == null || followUpPlanId.isBlank()) {
            throw new InvalidInputException("Follow-up plan id cannot be null or blank");
        }

        if (requestModel == null) {
            throw new InvalidInputException("Follow-up plan request model cannot be null");
        }

        if (requestModel.getAppointmentId() == null || requestModel.getAppointmentId().isBlank()) {
            throw new InvalidInputException("Appointment id cannot be null or blank");
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

        if (requestModel.getRecommendedFollowUpDate() == null) {
            throw new InvalidInputException("Recommended follow-up date cannot be null");
        }

        if (requestModel.getFollowUpReason() == null || requestModel.getFollowUpReason().isBlank()) {
            throw new InvalidInputException("Follow-up reason cannot be null or blank");
        }

        Optional<AppointmentFollowUpPlan> foundFollowUpPlan =
                appointmentFollowUpPlanRepository.findByFollowUpPlanId_FollowUpPlanId(followUpPlanId);

        if (foundFollowUpPlan.isEmpty()) {
            throw new NotFoundException("Follow-up plan not found with id: " + followUpPlanId);
        }

        AppointmentFollowUpPlan existingFollowUpPlan = foundFollowUpPlan.get();

        AppointmentResponseModel appointment =
                appointmentService.getAppointmentById(requestModel.getAppointmentId());

        PatientResponseModel patient =
                patientService.getPatientById(requestModel.getPatientId());

        DoctorResponseModel doctor =
                doctorService.getDoctorById(requestModel.getDoctorId());

        ClinicResponseModel clinic =
                clinicService.getClinicById(requestModel.getClinicId());

        // Invariant: recommendedFollowUpDate must be after original appointmentDateTime
        if (!requestModel.getRecommendedFollowUpDate().isAfter(appointment.getAppointmentDateTime())) {
            throw new InvalidInputException(
                    "Recommended follow-up date must be after the original appointment date and time"
            );
        }

        existingFollowUpPlan.setAppointmentId(new AppointmentIdentifier(requestModel.getAppointmentId()));
        existingFollowUpPlan.setPatientId(new PatientIdentifier(requestModel.getPatientId()));
        existingFollowUpPlan.setDoctorId(new DoctorIdentifier(requestModel.getDoctorId()));
        existingFollowUpPlan.setClinicId(new ClinicIdentifier(requestModel.getClinicId()));

        existingFollowUpPlan.updateFollowUpDetails(
                requestModel.getFollowUpReason(),
                requestModel.getRecommendedFollowUpDate()
        );

        AppointmentFollowUpPlan savedFollowUpPlan = appointmentFollowUpPlanRepository.save(existingFollowUpPlan);

        AppointmentFollowUpPlanResponseModel responseModel = new AppointmentFollowUpPlanResponseModel();

        responseModel.setFollowUpPlanId(savedFollowUpPlan.getFollowUpPlanId().getFollowUpPlanId());
        responseModel.setRecommendedFollowUpDate(savedFollowUpPlan.getRecommendedFollowUpDate());
        responseModel.setFollowUpReason(savedFollowUpPlan.getFollowUpReason());
        responseModel.setStatus(savedFollowUpPlan.getStatus());

        responseModel.setAppointmentId(appointment.getAppointmentId());
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

    @Override
    @Transactional
    public void deleteFollowUpPlan(String followUpPlanId) {

        if (followUpPlanId == null || followUpPlanId.isBlank()) {
            throw new InvalidInputException("Follow-up plan id cannot be null or blank");
        }

        Optional<AppointmentFollowUpPlan> foundFollowUpPlan =
                appointmentFollowUpPlanRepository.findByFollowUpPlanId_FollowUpPlanId(followUpPlanId);

        if (foundFollowUpPlan.isEmpty()) {
            throw new NotFoundException("Follow-up plan not found with id: " + followUpPlanId);
        }

        appointmentFollowUpPlanRepository.delete(foundFollowUpPlan.get());
    }

    @Override
    @Transactional
    public AppointmentFollowUpPlanResponseModel completeFollowUpPlan(String followUpPlanId) {

        if (followUpPlanId == null || followUpPlanId.isBlank()) {
            throw new InvalidInputException("Follow-up plan id cannot be null or blank");
        }

        Optional<AppointmentFollowUpPlan> foundFollowUpPlan =
                appointmentFollowUpPlanRepository.findByFollowUpPlanId_FollowUpPlanId(followUpPlanId);

        if (foundFollowUpPlan.isEmpty()) {
            throw new NotFoundException("Follow-up plan not found with id: " + followUpPlanId);
        }

        AppointmentFollowUpPlan followUpPlan = foundFollowUpPlan.get();
        followUpPlan.markAsCompleted();

        AppointmentFollowUpPlan savedFollowUpPlan = appointmentFollowUpPlanRepository.save(followUpPlan);

        AppointmentResponseModel appointment =
                appointmentService.getAppointmentById(savedFollowUpPlan.getAppointmentId().getAppointmentId());

        PatientResponseModel patient =
                patientService.getPatientById(savedFollowUpPlan.getPatientId().getPatientId());

        DoctorResponseModel doctor =
                doctorService.getDoctorById(savedFollowUpPlan.getDoctorId().getDoctorId());

        ClinicResponseModel clinic =
                clinicService.getClinicById(savedFollowUpPlan.getClinicId().getClinicId());

        AppointmentFollowUpPlanResponseModel responseModel = new AppointmentFollowUpPlanResponseModel();

        responseModel.setFollowUpPlanId(savedFollowUpPlan.getFollowUpPlanId().getFollowUpPlanId());
        responseModel.setRecommendedFollowUpDate(savedFollowUpPlan.getRecommendedFollowUpDate());
        responseModel.setFollowUpReason(savedFollowUpPlan.getFollowUpReason());
        responseModel.setStatus(savedFollowUpPlan.getStatus());

        responseModel.setAppointmentId(appointment.getAppointmentId());
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

    @Override
    @Transactional
    public AppointmentFollowUpPlanResponseModel cancelFollowUpPlan(String followUpPlanId) {

        if (followUpPlanId == null || followUpPlanId.isBlank()) {
            throw new InvalidInputException("Follow-up plan id cannot be null or blank");
        }

        Optional<AppointmentFollowUpPlan> foundFollowUpPlan =
                appointmentFollowUpPlanRepository.findByFollowUpPlanId_FollowUpPlanId(followUpPlanId);

        if (foundFollowUpPlan.isEmpty()) {
            throw new NotFoundException("Follow-up plan not found with id: " + followUpPlanId);
        }

        AppointmentFollowUpPlan followUpPlan = foundFollowUpPlan.get();
        followUpPlan.cancelPlan();

        AppointmentFollowUpPlan savedFollowUpPlan = appointmentFollowUpPlanRepository.save(followUpPlan);

        AppointmentResponseModel appointment =
                appointmentService.getAppointmentById(savedFollowUpPlan.getAppointmentId().getAppointmentId());

        PatientResponseModel patient =
                patientService.getPatientById(savedFollowUpPlan.getPatientId().getPatientId());

        DoctorResponseModel doctor =
                doctorService.getDoctorById(savedFollowUpPlan.getDoctorId().getDoctorId());

        ClinicResponseModel clinic =
                clinicService.getClinicById(savedFollowUpPlan.getClinicId().getClinicId());

        AppointmentFollowUpPlanResponseModel responseModel = new AppointmentFollowUpPlanResponseModel();

        responseModel.setFollowUpPlanId(savedFollowUpPlan.getFollowUpPlanId().getFollowUpPlanId());
        responseModel.setRecommendedFollowUpDate(savedFollowUpPlan.getRecommendedFollowUpDate());
        responseModel.setFollowUpReason(savedFollowUpPlan.getFollowUpReason());
        responseModel.setStatus(savedFollowUpPlan.getStatus());

        responseModel.setAppointmentId(appointment.getAppointmentId());
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