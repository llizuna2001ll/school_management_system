package com.digital_zone.subjectservice.services;

import com.digital_zone.subjectservice.entities.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getSubjectsByGradeId(Long gradeId);
    Subject getSubject(Long subjectId, String authorizationHeader);
    Subject addSubject(Subject subject);
    void deleteSubject(Long subjectId);
}
