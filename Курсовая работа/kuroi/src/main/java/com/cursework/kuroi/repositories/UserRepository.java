package com.cursework.kuroi.repositories;

import com.cursework.kuroi.models.Art;
import com.cursework.kuroi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserEmail(String userEmail);
    User findByUserNickName(String userNickName);


    // TODO: Посмотреть как в этот запрос поместить еще и Long ID
    @Query("SELECT p FROM User p WHERE p.userEmail LIKE %:keyword% OR p.userNickName LIKE %:keyword%")
    List<User> findByKeyword(@Param("keyword") String keyword);
}
