package com.digital_zone.gradeservice.services;

import com.digital_zone.gradeservice.entities.Grade;
import com.digital_zone.gradeservice.exceptions.GradeAlreadyExistsException;
import com.digital_zone.gradeservice.exceptions.GradeNotFoundException;
import com.digital_zone.gradeservice.models.UserModel;
import com.digital_zone.gradeservice.repositories.GradeRepository;
import com.digital_zone.gradeservice.restclients.UserServiceRestClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final UserServiceRestClient userServiceRestClient;

    public GradeServiceImpl(GradeRepository gradeRepository, UserServiceRestClient userServiceRestClient) {
        this.gradeRepository = gradeRepository;
        this.userServiceRestClient = userServiceRestClient;
    }

    @Override
    public List<Grade> getGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public Grade getGrade(Long id, String authorizationHeader) {
        Grade grade = gradeRepository.findById(id).orElseThrow(GradeNotFoundException::new);
        List<UserModel> users = userServiceRestClient.getUsersByGrade(grade.getName(),authorizationHeader);
        grade.setUsers(users);
        return grade;
    }

    @Override
    public Integer getStudentCount(Long id) {
        Grade grade = gradeRepository.findById(id).orElseThrow(GradeNotFoundException::new);
        return grade.getStudentCount();
    }

    @Override
    public Grade addGrade(Grade grade) {
        Grade oldGrade = gradeRepository.findByName(grade.getName());
        if(oldGrade != null){
            throw new GradeAlreadyExistsException();
        }
        grade.setStudentCount(0);
        return gradeRepository.save(grade);
    }

    @Override
    public void incrementStudentCount(String gradeName) {
        Grade grade = gradeRepository.findByName(gradeName);
        grade.setStudentCount(grade.getStudentCount()+1);
        gradeRepository.save(grade);
    }

    @Override
    public void decrementStudentCount(String gradeName) {
        Grade grade = gradeRepository.findByName(gradeName);
        grade.setStudentCount(grade.getStudentCount()-1);
        gradeRepository.save(grade);
    }
}
