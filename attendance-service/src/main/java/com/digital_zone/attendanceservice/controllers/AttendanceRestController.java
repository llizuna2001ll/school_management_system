package com.digital_zone.attendanceservice.controllers;

import com.digital_zone.attendanceservice.entites.Attendance;
import com.digital_zone.attendanceservice.services.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceRestController {

    private final AttendanceService attendanceService;

    public AttendanceRestController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public ResponseEntity<List<Attendance>> getAttendance(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long subjectId,
            @RequestParam(required = false) String date,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        List<Attendance> attendanceList = attendanceService.getAttendances(userId, subjectId, date, authorizationHeader);
        return new ResponseEntity<>(attendanceList, HttpStatus.OK);
    }

    @PostMapping("/addAttendance")
    public ResponseEntity<Attendance> addAttendance(@RequestBody Attendance attendance) {
        Attendance addedAttendance = attendanceService.addAttendance(attendance);
        return new ResponseEntity<>(addedAttendance, HttpStatus.CREATED);
    }

    @PatchMapping("/assign-justification/{attendanceId}")
    public ResponseEntity<Attendance> assignJustification(@PathVariable Long attendanceId, @RequestParam String justification) {
        Attendance updatedAttendance = attendanceService.assignJustification(justification, attendanceId);
        return new ResponseEntity<>(updatedAttendance, HttpStatus.OK);
    }




}
