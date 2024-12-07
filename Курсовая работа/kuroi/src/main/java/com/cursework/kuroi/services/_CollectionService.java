package com.cursework.kuroi.services;


import com.cursework.kuroi.models.Art;
import com.cursework.kuroi.models.User;
import com.cursework.kuroi.models._UserCollection;
import com.cursework.kuroi.repositories._UserCollectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class _CollectionService {

    private final _UserCollectionRepository userCollectionRepository;

    public void addToCollection(User user, Art art) {
        if(userCollectionRepository.getCollection_ByUserId(user.getId()) != null) {
            userCollectionRepository.getCollection_ByUserId(user.getId()).getArt().add(art);
        }
        else {
            List<Art> temp_array = Arrays.asList(art);
            _UserCollection temp_collection = new _UserCollection(user, temp_array);
            userCollectionRepository.save(temp_collection);
        }
    }
}
