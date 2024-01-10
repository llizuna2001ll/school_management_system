package com.digital_zone.gradeservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
