package com.digital_zone.subjectservice.services;

import com.digital_zone.subjectservice.entities.Subject;
import com.digital_zone.subjectservice.exceptions.SubjectNotFoundException;
import com.digital_zone.subjectservice.models.UserModel;
import com.digital_zone.subjectservice.repositories.SubjectRepository;
import com.digital_zone.subjectservice.restclient.UserServiceRestClient;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final UserServiceRestClient userServiceRestClient;

    public SubjectServiceImpl(SubjectRepository subjectRepository, UserServiceRestClient userServiceRestClient) {
        this.subjectRepository = subjectRepository;
        this.userServiceRestClient = userServiceRestClient;
    }

    @Override
    public List<Subject> getSubjectsByGradeId(Long gradeId) {
        return subjectRepository.findAllByGradeId(gradeId);
    }

    @Override
    public Subject getSubject(Long subjectId, String authorizationHeader) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(SubjectNotFoundException::new);
        UserModel professor = userServiceRestClient.getUser(subject.getProfessorId(), authorizationHeader);
        subject.setProfessor(professor);
        return subject;
    }

    @Override
    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void deleteSubject(Long subjectId) {
        subjectRepository.deleteById(subjectId);
    }
}
