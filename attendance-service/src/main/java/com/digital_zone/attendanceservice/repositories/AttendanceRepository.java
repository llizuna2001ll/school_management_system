package com.digital_zone.attendanceservice.repositories;

import com.digital_zone.attendanceservice.entites.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}