package com.cursework.kuroi.repositories;

import com.cursework.kuroi.models.UserCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface _CollectionRepository extends JpaRepository <UserCollection, Long> {
    UserCollection getCollection_ByUserId(Long userID);
}
