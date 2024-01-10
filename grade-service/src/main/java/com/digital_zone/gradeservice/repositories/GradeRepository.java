package com.digital_zone.gradeservice.repositories;

import com.digital_zone.gradeservice.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
   Grade findByName(String name);
}