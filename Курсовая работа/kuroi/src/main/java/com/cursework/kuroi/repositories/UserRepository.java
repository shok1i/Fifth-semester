package com.cursework.kuroi.repositories;

import com.cursework.kuroi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserEmail(String userEmail);
    User findByUserNickName(String userNickName);
}
