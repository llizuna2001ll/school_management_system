package com.digital_zone.userservice.repositories;

import com.digital_zone.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByVerificationCode(String verificationCode);
}