package com.cursework.kuroi.services;

import com.cursework.kuroi.models.Art;
import com.cursework.kuroi.models.User;
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
        _Likes likes = new _Likes();

        likes.getLiked_users().add(user);
        likes.getLiked_arts().add(art);

        user.getLikes().add(likes);
        art.getLikes().add(likes);

        likesRepository.save(likes);
    }

    public void deleteLike(User user, Art art) {
        _Likes likes = likesRepository.getLikeByUserAndArt(user.getUserID(), art.getArtID());

        if (likes != null) {
            likesRepository.delete(likes);
        }
    }
}
