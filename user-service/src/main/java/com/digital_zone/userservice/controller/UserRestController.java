package com.digital_zone.userservice.controller;

import com.digital_zone.userservice.dtos.AddEmailRequest;
import com.digital_zone.userservice.dtos.UserRequest;
import com.digital_zone.userservice.dtos.UserResponse;
import com.digital_zone.userservice.services.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/grade")
    public ResponseEntity<List<UserResponse>> getUsersByGrade(@RequestParam String grade) {
        List<UserResponse> users = userService.getUsersByGrade(grade);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
        UserResponse user = userService.getUser(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest user) {
        UserResponse createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @DeleteMapping("deleteUser/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addEmail")
    public ResponseEntity<UserResponse> addEmail(@RequestBody AddEmailRequest addEmailRequest, HttpServletRequest httpRequest) throws MessagingException, IOException {
        UserResponse user = userService.addEmail(addEmailRequest, getSiteURL(httpRequest));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/verify")
    public ModelAndView verifyUser(@Param("code") String code) {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.verifyEmail(code)) {
            modelAndView.setViewName("verify_success");
        } else {
            modelAndView.setViewName("verify_failed");
        }
        return modelAndView;
    }

    @PutMapping("/{userId}/assignGrade")
    public ResponseEntity<UserResponse> assignGrade(@PathVariable Long userId, @RequestParam String grade) {
        UserResponse user = userService.assignGrade(grade, userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/{userId}/unassignGrade")
    public ResponseEntity<UserResponse> unassignGrade(@PathVariable Long userId, @RequestParam String grade) {
        UserResponse user = userService.unassignGrade(grade, userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
