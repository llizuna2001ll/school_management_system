package com.digital_zone.userservice.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "grade-service", url = "http://localhost:8081/api/v1/grades")
public interface GradeServiceRestClient {

    @PutMapping("/addStudent")
    void incrementStudentCount(@RequestParam String grade);

    @PutMapping ("/removeStudent")
    void decrementStudentCount(@RequestParam String grade);
}

