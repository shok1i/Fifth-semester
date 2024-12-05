package com.cursework.kuroi.repositories;

import com.cursework.kuroi.models._UserCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface _UserCollectionRepository extends JpaRepository <_UserCollection, Long> {
}
