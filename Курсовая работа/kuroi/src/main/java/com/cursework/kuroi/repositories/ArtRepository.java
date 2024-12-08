package com.cursework.kuroi.repositories;

import com.cursework.kuroi.models.Art;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ArtRepository extends JpaRepository<Art, Long>, JpaSpecificationExecutor<Art> {
}
