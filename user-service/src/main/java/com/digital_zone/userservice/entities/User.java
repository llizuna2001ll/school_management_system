package com.digital_zone.userservice.entities;

import com.digital_zone.userservice.dtos.UserRequest;
import com.digital_zone.userservice.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Builder
@Table(name = "users", indexes = {
        @Index(name = "idx_username", columnList = "username"),
        @Index(name = "idx_verification_code", columnList = "verificationCode")
})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String birthDate;
    private String birthPlace;
    private String address;
    private String phoneNumber;
    private String registrationDate;
    @Enumerated(EnumType.STRING)
    private Roles role;
    @JsonIgnore
    private String verificationCode;
    @JsonIgnore
    private String resetPasswordCode;
    @JsonIgnore
    private boolean enabled;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime creationTime;

    public static User toUser(UserRequest userRequest){
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .address(userRequest.getAddress())
                .birthDate(userRequest.getBirthDate())
                .birthPlace(userRequest.getBirthPlace())
                .phoneNumber(userRequest.getPhoneNumber())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

