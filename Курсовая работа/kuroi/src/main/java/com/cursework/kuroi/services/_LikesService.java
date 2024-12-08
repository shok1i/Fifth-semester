package com.cursework.kuroi.services;

import com.cursework.kuroi.models.Art;
import com.cursework.kuroi.models.User;
import com.cursework.kuroi.models.UserCollection;
import com.cursework.kuroi.models._Likes;
import com.cursework.kuroi.repositories._LikesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class _LikesService {
    private final _LikesRepository likesRepository;

    public void addLike(User user, Art art) {
        _Likes likes = likesRepository.getByUserUserID(user.getUserID());

        if (likes == null) {
            likes = new _Likes();
            likes.setUser(user);
        }

        likes.getLiked_arts().add(art);
        user.setLikes(likes);

        art.getLikes().add(likes);

        likesRepository.save(likes);
    }

    public void deleteLike(User user, Art art) {
        _Likes likes = likesRepository.getByUserUserID(user.getUserID());

        if (likes != null) {
            likes.getLiked_arts().remove(art);
            likesRepository.save(likes);
        }
    }
}
