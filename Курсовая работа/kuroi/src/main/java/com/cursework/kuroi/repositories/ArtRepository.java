package com.cursework.kuroi.repositories;

import com.cursework.kuroi.models.Art;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtRepository extends JpaRepository<Art, Long> {
    Art getArt_ById(Long artId);
    List<Art> getArts_ByAuthorId(Long id);

    // Запрос в SQL аблицу для поиска по содержанию слова в названии, описании или в авторе
    @Query("SELECT p FROM Art p WHERE p.title LIKE %:keyword% OR p.description LIKE %:keyword% OR p.author.userNickName LIKE %:keyword%")
    List<Art> getArts_ByKeyword(@Param("keyword") String keyword);


}
