package com.cursework.kuroi.repositories;

import com.cursework.kuroi.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Query("SELECT l FROM Likes l JOIN l.liked_users u JOIN l.liked_arts a WHERE u.userID = :userId AND a.artID = :artId")
    Likes getLikeByUserAndArt(@Param("userId") Long userId, @Param("artId") Long artId);

}
