package com.digital_zone.gradeservice.entities;

import com.digital_zone.gradeservice.models.SubjectModel;
import com.digital_zone.gradeservice.models.UserModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer studentCount;
    @Transient
    private List<UserModel> users;
    @Transient
    private List<SubjectModel> subjects;
}
