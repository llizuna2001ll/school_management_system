package com.digital_zone.subjectservice.restclient;

import com.digital_zone.subjectservice.models.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", url = "http://localhost:8080/api/v1/users")
public interface UserServiceRestClient {
    @GetMapping("/{userId}")
    UserModel getUser(@PathVariable Long userId, @RequestHeader("Authorization") String authorizationHeader);
}