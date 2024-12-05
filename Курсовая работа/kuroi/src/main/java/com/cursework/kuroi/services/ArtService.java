package com.cursework.kuroi.services;

import com.cursework.kuroi.models.Art;
import com.cursework.kuroi.models.Image;
import com.cursework.kuroi.models.User;
import com.cursework.kuroi.repositories.ArtRepository;
import com.cursework.kuroi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArtService {
    private final ArtRepository artRepository;
    private final UserRepository userRepository;

    public List<Art> getArtsByKeyword(String keyword) {
        if (keyword != null) return artRepository.findByKeyword(keyword);
        return artRepository.findAll();
    }

    public Art getArtById(Long id) {
        return artRepository.findById(id).orElse(null);
    }

    public boolean addArt(Principal principal, Art art, MultipartFile file) throws IOException {
        if (file.getSize() != 0) {
            art.setAuthor(getUserByPrincipal(principal));

            Image image = new Image();
            image.setPath("img" + image.getId());
            image.setContentType(file.getContentType());
            image.setSize(file.getSize());
            image.setBytes(file.getBytes());

            art.setImage(image);
            artRepository.save(art);
            return true;
        }
        return false;
    }

    public boolean deleteArt(Principal principal, Long id) {
        Art tempArt = artRepository.findById(id).orElse(null);
        User tempUser = getUserByPrincipal(principal);
        if (tempArt != null && tempUser != null && tempArt.getAuthor().getId().equals(tempUser.getId())) {
            artRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByUserEmail(principal.getName());
    }
}
