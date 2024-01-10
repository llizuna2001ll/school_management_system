package com.digital_zone.gradeservice.controller;

import com.digital_zone.gradeservice.entities.Grade;
import com.digital_zone.gradeservice.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/grades")
public class GradeRestController {

    private final GradeService gradeService;

    @Autowired
    public GradeRestController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping
    public ResponseEntity<List<Grade>> getGrades() {
        List<Grade> grades = gradeService.getGrades();
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable("id") Long id, @RequestHeader("Authorization") String authorizationHeader) {
        Grade grade = gradeService.getGrade(id,authorizationHeader);
        return new ResponseEntity<>(grade, HttpStatus.OK);
    }

    @GetMapping("/{id}/studentCount")
    public ResponseEntity<Integer> getStudentCountById(@PathVariable("id") Long id) {
        Integer studentCount = gradeService.getStudentCount(id);
        return new ResponseEntity<>(studentCount, HttpStatus.OK);
    }

    @PostMapping("/addGrade")
    public ResponseEntity<Grade> addGrade(@RequestBody Grade grade) {
        return new ResponseEntity<>(gradeService.addGrade(grade), HttpStatus.OK);
    }

    @PutMapping("/addStudent")
    public ResponseEntity<Void> incrementStudentCount(@RequestParam String grade) {
        gradeService.incrementStudentCount(grade);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/removeStudent")
    public ResponseEntity<Void> decrementStudentCount(@RequestParam String grade) {
        gradeService.decrementStudentCount(grade);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
