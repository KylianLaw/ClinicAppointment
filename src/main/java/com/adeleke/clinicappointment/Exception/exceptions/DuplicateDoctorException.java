package com.adeleke.clinicappointment.Exception.exceptions;

public class DuplicateDoctorException extends RuntimeException {
    public DuplicateDoctorException(String message) {
        super(message);
    }
}