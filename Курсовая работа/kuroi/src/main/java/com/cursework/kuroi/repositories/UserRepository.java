package com.cursework.kuroi.repositories;

import com.cursework.kuroi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUser_ById(Long userId);
    User getUser_ByUserEmail(String userEmail);
    User getUser_ByUserNickName(String userNickName);

    @Query("SELECT p FROM User p WHERE p.id = :keyword OR p.userEmail LIKE %:keyword% OR p.userNickName LIKE %:keyword%")
    List<User> getUsers_ByKeyword(@Param("keyword") String keyword);
}
