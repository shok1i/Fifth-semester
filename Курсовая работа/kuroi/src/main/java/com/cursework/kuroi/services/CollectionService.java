package com.cursework.kuroi.services;


import com.cursework.kuroi.models.Art;
import com.cursework.kuroi.models.User;
import com.cursework.kuroi.models.UserCollection;
import com.cursework.kuroi.repositories._CollectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class CollectionService {

    private final _CollectionRepository userCollectionRepository;

    public void addToCollection(User user, Art art) {
        UserCollection userCollection = userCollectionRepository.getCollection_ByUserId(user.getId());

        if(userCollection != null) {
            userCollection.getArts().add(art);
        }
        else {
            userCollection = new UserCollection();
            userCollection.setUser(user);
            userCollection.setArts(Collections.singletonList(art));
        }

        userCollectionRepository.save(userCollection);
    }

    public void deleteFromCollection(User user, Art art) {
        UserCollection userCollection = userCollectionRepository.getCollection_ByUserId(user.getId());

        if(userCollection != null) {
            userCollection.getArts().remove(art);
            userCollectionRepository.save(userCollection);
        }
    }
}
