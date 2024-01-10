package com.digital_zone.gradeservice.exceptions;

public class GradeNotFoundException extends RuntimeException {
    public GradeNotFoundException() {
        super("User Not Found");
    }

    public GradeNotFoundException(String message) {
        super(message);
    }

    public GradeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
