package com.cursework.kuroi.services;

import com.cursework.kuroi.models.Art;
import com.cursework.kuroi.models.User;
import com.cursework.kuroi.models.Likes;
import com.cursework.kuroi.repositories.LikesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;

    public void addLike(User user, Art art) {
        Likes likes = new Likes();

        likes.getLiked_users().add(user);
        likes.getLiked_arts().add(art);

        user.getLikes().add(likes);
        art.getLikes().add(likes);

        likesRepository.save(likes);
    }

    public void deleteLike(User user, Art art) {
        Likes likes = likesRepository.getLikeByUserAndArt(user.getUserID(), art.getArtID());

        if (likes != null) {
            likesRepository.delete(likes);
        }
    }
}
