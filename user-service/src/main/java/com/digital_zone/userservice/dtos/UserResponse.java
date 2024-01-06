package com.digital_zone.userservice.dtos;

import com.digital_zone.userservice.entities.User;
import com.digital_zone.userservice.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String birthDate;
    private String birthPlace;
    private String address;
    private String phoneNumber;
    private Roles role;

    public static UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .birthPlace(user.getBirthPlace())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .build();
    }

}
