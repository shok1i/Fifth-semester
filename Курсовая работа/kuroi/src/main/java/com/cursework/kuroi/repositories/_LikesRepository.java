package com.cursework.kuroi.repositories;

import com.cursework.kuroi.models._Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface _LikesRepository extends JpaRepository<_Likes, Long> {
}
