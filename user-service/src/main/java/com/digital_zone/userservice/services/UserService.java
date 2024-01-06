package com.digital_zone.userservice.services;


import com.digital_zone.userservice.dtos.AddEmailRequest;
import com.digital_zone.userservice.dtos.UserRequest;
import com.digital_zone.userservice.dtos.UserResponse;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {
    List<UserResponse> getUsers();
    UserResponse getUser(Long userId);
    UserResponse createUser(UserRequest user);
    void deleteUser(Long userId);
    UserResponse addEmail(AddEmailRequest addEmailRequest, String siteURL) throws MessagingException, UnsupportedEncodingException;
    boolean verifyEmail(String verificationCode);
}
