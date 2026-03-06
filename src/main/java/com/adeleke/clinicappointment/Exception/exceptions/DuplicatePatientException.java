package com.adeleke.clinicappointment.Exception.exceptions;


public class DuplicatePatientException extends RuntimeException {
    public DuplicatePatientException(String message) {
        super(message);
    }
}