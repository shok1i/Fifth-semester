package com.cursework.kuroi.services;

import com.cursework.kuroi.models.Art;
import com.cursework.kuroi.models.Image;
import com.cursework.kuroi.models.User;
import com.cursework.kuroi.repositories.ArtRepository;
import com.cursework.kuroi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArtService {
    private final ArtRepository artRepository;
    private final UserRepository userRepository;

    public List<Art> getArts(String keyword, Long date) {
        Specification<Art> spec = Specification.where(null);

        // Добавляем условия по ключевому слову, если оно передано
        if (keyword != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get("title"), "%" + keyword + "%"),
                            criteriaBuilder.like(root.get("description"), "%" + keyword + "%"),
                            criteriaBuilder.like(root.get("author").get("userNickName"), "%" + keyword + "%")
                    )
            );
        }

        // Добавляем условия по дате, если она передана
        if (date != null) {
            LocalDate filterDate = LocalDate.now().minusDays(date);
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("publishDate"), filterDate)
            );
        }

        // сортировка по размеру списка Likes
        spec = spec.and((root, query, criteriaBuilder) -> {
            assert query != null;
            query.orderBy(criteriaBuilder.desc(criteriaBuilder.size(root.get("likes"))));
            return criteriaBuilder.conjunction();
        });

        // Выполняем запрос с построенной спецификацией
        return artRepository.findAll(spec);
    }

    public Art getArtById(Long id) {
        return artRepository.findById(id).orElse(null);
    }

    public boolean addArt(Principal principal, Art art, MultipartFile file) throws IOException {
        User user = getUserByPrincipal(principal);


        if (file.getSize() != 0) {
            Image image = new Image();

            art.setAuthor(user);

            image.setPath("img" + image.getImageID());
            image.setContentType(file.getContentType());
            image.setSize(file.getSize());
            image.setBytes(file.getBytes());
            image.setArt(art);

            art.setImage(image);

            user.addArt(art);

            artRepository.save(art);
            userRepository.save(user);

            return true;
        }
        return false;
    }

    public boolean deleteArt(Principal principal, Long id) {
        Art tempArt = artRepository.findById(id).orElse(null);
        User tempUser = getUserByPrincipal(principal);
        if (tempArt != null && tempUser != null && tempArt.getAuthor().getUserID().equals(tempUser.getUserID())) {
            artRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.getUser_ByUserEmail(principal.getName());
    }
}
