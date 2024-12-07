package com.cursework.kuroi.controllers;

import com.cursework.kuroi.models.Art;
import com.cursework.kuroi.models.User;
import com.cursework.kuroi.services.ArtService;
import com.cursework.kuroi.services.UserService;
import com.cursework.kuroi.services.CollectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
@RequiredArgsConstructor
public class CollectionController {
    private final CollectionService collectionService;
    private final UserService userService;
    private final ArtService artService;

    /// Обработка POST-запроса
    @PostMapping("/api/collection")
    public ResponseEntity<String> addToCollection(@RequestParam(name = "user") Long userID, @RequestParam(name = "art") Long artID) {
        User user = userService.getUserById(userID);
        Art art = artService.getArtById(artID);

        log.info("userID {}; artID {}", userID, artID);

        if (user != null) {
            collectionService.addToCollection(user, art);
            return ResponseEntity.ok("Add to collection");
        }
        return ResponseEntity.notFound().build();
    }

    /// Обработка DELETE-запроса
    @DeleteMapping("/api/collection")
    public ResponseEntity<String> deleteFromCollection(@RequestParam("user") Long userID, @RequestParam("art") Long artID) {
        User user = userService.getUserById(userID);
        Art art = artService.getArtById(artID);

        if (user != null) {
            collectionService.deleteFromCollection(user, art);
            return ResponseEntity.ok("Add to collection");
        }
        return ResponseEntity.notFound().build();
    }
}
