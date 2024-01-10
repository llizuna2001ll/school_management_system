package com.digital_zone.gradeservice.services;

import com.digital_zone.gradeservice.entities.Grade;

import java.util.List;

public interface GradeService {
    List<Grade> getGrades();
    Grade getGrade(Long id, String authorizationHeader);
    Integer getStudentCount(Long id);
    Grade addGrade(Grade grade);
    void incrementStudentCount(String grade);
    void decrementStudentCount(String grade);
}
