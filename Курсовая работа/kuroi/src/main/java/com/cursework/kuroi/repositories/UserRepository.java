package com.cursework.kuroi.repositories;

import com.cursework.kuroi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.Principal;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserEmail(String userEmail);

    User findByNickname(String nickname);

    User findByPrinciple(Principal principal);
}
