package com.cursework.kuroi.services;

import com.cursework.kuroi.models.Art;
import com.cursework.kuroi.models.User;
import com.cursework.kuroi.models.UserCollection;
import com.cursework.kuroi.repositories.CollectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CollectionService {
    private final CollectionRepository userCollectionRepository;

    public void addToCollection(User user, Art art) {
        UserCollection userCollection = userCollectionRepository.getByUserUserID(user.getUserID());

        if (userCollection == null) {
            userCollection = new UserCollection();
            userCollection.setUser(user);
        }

        userCollection.getCollections_arts().add(art);
        user.setUserCollection(userCollection);

        art.getUserCollections().add(userCollection);

        userCollectionRepository.save(userCollection);
    }

    public void deleteFromCollection(User user, Art art) {
        UserCollection userCollection = userCollectionRepository.getByUserUserID(user.getUserID());

        if (userCollection != null) {
            userCollection.getCollections_arts().remove(art);
            userCollectionRepository.save(userCollection);
        }
    }
}
