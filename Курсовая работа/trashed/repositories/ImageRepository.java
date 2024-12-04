package com.cursework.kuroi.repositories;

import com.cursework.kuroi.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
