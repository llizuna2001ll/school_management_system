package com.digital_zone.userservice.entities;

import com.digital_zone.userservice.dtos.UserRequest;
import com.digital_zone.userservice.enums.Roles;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthDate;
    private String birthPlace;
    private String address;
    private String phoneNumber;
    private Roles role;

    public static User toUser(UserRequest userRequest){
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .address(userRequest.getAddress())
                .email(userRequest.getEmail())
                .birthDate(userRequest.getBirthDate())
                .birthPlace(userRequest.getBirthPlace())
                .phoneNumber(userRequest.getPhoneNumber())
                .build();
    }
}

