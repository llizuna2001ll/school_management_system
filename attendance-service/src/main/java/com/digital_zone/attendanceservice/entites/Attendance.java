package com.digital_zone.attendanceservice.entites;

import com.digital_zone.attendanceservice.enums.AttendanceState;
import com.digital_zone.attendanceservice.models.UserModel;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private AttendanceState attendanceState;
    private String justification;
    private String date;
    private Long userId;
    private Long subjectId;
    @Transient
    private UserModel user;
}
