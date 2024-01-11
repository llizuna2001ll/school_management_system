package com.digital_zone.gradeservice.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectModel {
    private Long id;
    private String name;
    private String description;
    private Long professorId;
}
