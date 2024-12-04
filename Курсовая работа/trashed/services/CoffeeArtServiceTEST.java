package com.cursework.kuroi.services;

import com.cursework.kuroi.models.CoffeeArtTEST;
import com.cursework.kuroi.models.Image;
import com.cursework.kuroi.models.User;
import com.cursework.kuroi.repositories.CoffeeArtRepositoryTEST;
import com.cursework.kuroi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoffeeArtServiceTEST {
    private final CoffeeArtRepositoryTEST coffeeArtRepository;
    private final UserRepository userRepository;

    public List<CoffeeArtTEST> listCoffeeArt(String title) {
        if (title != null) return coffeeArtRepository.findByTitle(title);
        return coffeeArtRepository.findAll();
    }

    public void saveCoffeeArt(Principal principal, CoffeeArtTEST coffeeArt, MultipartFile file) throws IOException {
        Image image;

        if (file.getSize() != 0) {
            image = toImageEntity(file);
            coffeeArt.setImage(image);
        }

        coffeeArt.setUser(getUserByPrinciple(principal));

        log.info("Saving new product. Title: {}, with tags {}", coffeeArt.getTitle(), coffeeArt.getTagTESTS());
        coffeeArtRepository.save(coffeeArt);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setPath(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setContent(file.getBytes());
        return image;
    }

    public User getUserByPrinciple(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void deleteCoffeeArt(Long id) {
        coffeeArtRepository.deleteById(id);
    }

    public CoffeeArtTEST getCoffeeArtById(Long id) {
        return coffeeArtRepository.findById(id).orElse(null);
    }
}
