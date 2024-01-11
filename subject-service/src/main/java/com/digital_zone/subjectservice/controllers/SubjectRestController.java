package com.digital_zone.subjectservice.controllers;

import com.digital_zone.subjectservice.entities.Subject;
import com.digital_zone.subjectservice.exceptions.SubjectNotFoundException;
import com.digital_zone.subjectservice.services.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectRestController {

    private final SubjectService subjectService;

    public SubjectRestController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getSubjectsByGradeId(@RequestParam Long gradeId) {
        List<Subject> subjects = subjectService.getSubjectsByGradeId(gradeId);
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<Subject> getSubject(@PathVariable Long subjectId, @RequestHeader("Authorization") String authorizationHeader) {
        try {
            Subject subject = subjectService.getSubject(subjectId, authorizationHeader);
            return ResponseEntity.ok(subject);
        } catch (SubjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/addSubject")
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject) {
        Subject addedSubject = subjectService.addSubject(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedSubject);
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long subjectId) {
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.noContent().build();
    }
}
