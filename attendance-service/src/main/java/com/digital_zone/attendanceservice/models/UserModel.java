package com.digital_zone.attendanceservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserModel {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
}

