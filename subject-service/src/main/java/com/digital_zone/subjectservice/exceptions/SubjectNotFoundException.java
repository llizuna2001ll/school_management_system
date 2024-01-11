package com.digital_zone.subjectservice.exceptions;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException() {
        super("Subject Not Found");
    }

    public SubjectNotFoundException(String message) {
        super(message);
    }

    public SubjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
