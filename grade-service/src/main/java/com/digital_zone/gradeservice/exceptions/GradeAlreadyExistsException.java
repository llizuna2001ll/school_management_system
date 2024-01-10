package com.digital_zone.gradeservice.exceptions;

public class GradeAlreadyExistsException extends RuntimeException{
    public GradeAlreadyExistsException() {
        super("Grade Already Exists");
    }

    public GradeAlreadyExistsException(String message) {
        super(message);
    }

    public GradeAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
