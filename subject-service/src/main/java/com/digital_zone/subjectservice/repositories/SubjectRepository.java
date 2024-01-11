package com.digital_zone.subjectservice.repositories;

import com.digital_zone.subjectservice.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{
    List<Subject> findAllByGradeId(Long gradeId);
}