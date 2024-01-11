package com.digital_zone.gradeservice.restclients;

import com.digital_zone.gradeservice.models.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "user-service", url = "http://localhost:8080/api/v1/users")
public interface UserServiceRestClient {
    @GetMapping("/grade")
    List<UserModel> getUsersByGrade(@RequestParam String grade, @RequestHeader("Authorization") String authorizationHeader);
}