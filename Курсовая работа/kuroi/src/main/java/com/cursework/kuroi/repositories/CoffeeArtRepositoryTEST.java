package com.cursework.kuroi.repositories;

import com.cursework.kuroi.models.CoffeeArtTEST;
import com.cursework.kuroi.models.TagTEST;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoffeeArtRepositoryTEST extends JpaRepository<CoffeeArtTEST, Long> {
    List<CoffeeArtTEST> findByTitle(String title);

//    List<CoffeeArtTEST> findByTag(List<TagTEST> tags);
}
