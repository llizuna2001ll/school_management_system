package com.digital_zone.attendanceservice.exceptions;

public class AttendanceNotFoundException extends RuntimeException {

    public AttendanceNotFoundException() {
        super("Attendance Not Found");
    }

    public AttendanceNotFoundException(String message) {
        super(message);
    }

    public AttendanceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
