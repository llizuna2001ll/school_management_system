package com.digital_zone.attendanceservice.services;

import com.digital_zone.attendanceservice.entites.Attendance;

import java.util.List;

public interface AttendanceService {
    Attendance addAttendance(Attendance attendance);
    Attendance assignJustification(String justification, Long attendanceId);
    List<Attendance> getAttendances(Long userId, Long subjectId, String date, String authorizationHeader);

}
