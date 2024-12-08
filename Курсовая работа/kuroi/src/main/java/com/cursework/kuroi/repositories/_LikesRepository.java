package com.cursework.kuroi.repositories;

import com.cursework.kuroi.models.Art;
import com.cursework.kuroi.models.User;
import com.cursework.kuroi.models._Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface _LikesRepository extends JpaRepository<_Likes, Long> {

    @Query("SELECT l FROM _Likes l JOIN l.liked_users u JOIN l.liked_arts a WHERE u.userID = :userId AND a.artID = :artId")
    _Likes getLikeByUserAndArt(@Param("userId") Long userId, @Param("artId") Long artId);

}
