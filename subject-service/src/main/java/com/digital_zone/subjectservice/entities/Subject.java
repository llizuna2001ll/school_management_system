package com.digital_zone.subjectservice.entities;

import com.digital_zone.subjectservice.models.UserModel;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long professorId;
    private Long gradeId;
    @Transient
    private UserModel professor;
}
