package com.digital_zone.userservice.services;

import com.digital_zone.userservice.dtos.UserRequest;
import com.digital_zone.userservice.dtos.UserResponse;
import com.digital_zone.userservice.entities.User;
import com.digital_zone.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();

        List<UserResponse> userResponses = new ArrayList<>();

        for (User user : users){
            userResponses.add(UserResponse.toUserResponse(user));
        }

        return userResponses;
    }

    @Override
    public UserResponse getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        return UserResponse.toUserResponse(user);
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = userRepository.save(User.toUser(userRequest));

        return UserResponse.toUserResponse(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
