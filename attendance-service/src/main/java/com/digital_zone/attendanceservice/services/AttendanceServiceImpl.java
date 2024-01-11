package com.digital_zone.attendanceservice.services;

import com.digital_zone.attendanceservice.entites.Attendance;
import com.digital_zone.attendanceservice.exceptions.AttendanceNotFoundException;
import com.digital_zone.attendanceservice.models.UserModel;
import com.digital_zone.attendanceservice.repositories.AttendanceRepository;
import com.digital_zone.attendanceservice.restclient.UserServiceRestClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserServiceRestClient userServiceRestClient;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, UserServiceRestClient userServiceRestClient) {
        this.attendanceRepository = attendanceRepository;
        this.userServiceRestClient = userServiceRestClient;
    }

    @Override
    public Attendance addAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance assignJustification(String justification, Long attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId).orElseThrow(AttendanceNotFoundException::new);
        attendance.setJustification(justification);
        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> getAttendances(Long userId, Long subjectId, String date, String authorizationHeader) {
        List<Attendance> attendanceList = attendanceRepository.findAll();

        if (userId != null) {
            attendanceList = attendanceList.stream()
                    .filter(attendance -> attendance.getUserId().equals(userId))
                    .collect(Collectors.toList());
        }

        if (subjectId != null) {
            attendanceList = attendanceList.stream()
                    .filter(attendance -> attendance.getSubjectId().equals(subjectId))
                    .collect(Collectors.toList());
        }

        if (date != null && !date.isEmpty()) {
            attendanceList = attendanceList.stream()
                    .filter(attendance -> attendance.getDate().equals(date))
                    .collect(Collectors.toList());
        }

        for (Attendance attendance : attendanceList){
            UserModel user = userServiceRestClient.getUser(attendance.getUserId(), authorizationHeader);
            attendance.setUser(user);
        }

        return attendanceList;
    }
}
