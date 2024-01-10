package com.digital_zone.userservice.dtos;

import com.digital_zone.userservice.enums.Sexe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String firstName;
    private String lastName;
    private String password;
    private String birthDate;
    private String birthPlace;
    private String address;
    private String phoneNumber;
    private Sexe sexe;
    private String grade;
}
