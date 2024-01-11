package com.digital_zone.gradeservice.restclients;

import com.digital_zone.gradeservice.models.SubjectModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "subject-service", url = "http://localhost:8082/api/v1/subjects")
public interface SubjectServiceRestClient {
    @GetMapping
    List<SubjectModel> getSubjectsByGradeId(@RequestParam Long gradeId);
}
