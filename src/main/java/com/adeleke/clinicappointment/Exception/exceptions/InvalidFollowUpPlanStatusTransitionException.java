package com.adeleke.clinicappointment.Exception.exceptions;

public class InvalidFollowUpPlanStatusTransitionException extends RuntimeException {
    public InvalidFollowUpPlanStatusTransitionException(String message) {
        super(message);
    }
}