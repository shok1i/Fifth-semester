package com.cursework.kuroi.controllers;

import com.cursework.kuroi.models.Art;
import com.cursework.kuroi.models.User;
import com.cursework.kuroi.services._CollectionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class _UserCollectionController {
    _CollectionService collectionService;

    @PostMapping("/collection")
    public void addToCollection(@RequestParam("user") User user, @RequestParam("art") Art art) {
        collectionService.addToCollection(user, art);
    }
}
