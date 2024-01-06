package com.digital_zone.userservice.services;


import com.digital_zone.userservice.dtos.UserRequest;
import com.digital_zone.userservice.dtos.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getUsers();
    UserResponse getUser(Long userId);
    UserResponse createUser(UserRequest user);
    void deleteUser(Long userId);
}
