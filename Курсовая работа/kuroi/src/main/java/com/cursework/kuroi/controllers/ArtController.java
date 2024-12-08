package com.cursework.kuroi.controllers;

import com.cursework.kuroi.models.Art;
import com.cursework.kuroi.models.User;
import com.cursework.kuroi.models.Likes;
import com.cursework.kuroi.repositories.UserRepository;
import com.cursework.kuroi.services.ArtService;
import com.cursework.kuroi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArtController {
    private final ArtService artService;

    private final UserService userService;
    private final UserRepository userRepository;

    // Обработка GET-запросов
    @GetMapping("/gallery")
    public String products(@RequestParam(name = "searchWord", required = false) String title, @RequestParam(name = "days", required = false) Long days, Principal principal, Model model) {
        List<Art> arts = artService.getArts(title, days); // Получаем список объектов Art

        if (days != null) {
            LocalDate filterLikeDate = LocalDate.now().minusDays(days); // Рассчитываем дату для фильтрации

            // Фильтруем лайки внутри каждого объекта Art
            arts.forEach(art -> {
                List<Likes> likes = art.getLikes(); // Получаем список лайков для конкретного Art
                likes.removeIf(like -> like.getLikeDate().isBefore(filterLikeDate)); // Удаляем лайки, сделанные до определенной даты
            });
        }

        // Добавляем отфильтрованный список arts в модель
        model.addAttribute("arts", arts);
        model.addAttribute("currentUser", artService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", title);

        return "gallery";
    }

    @GetMapping("/{nickname}")
    public String userProducts(@PathVariable String nickname, Principal principal, Model model) {
        User currentUser = userService.getUserByPrinciple(principal);

        if (userRepository.getUser_ByUserNickName(nickname) != null) {
            User find = userRepository.getUser_ByUserNickName(nickname);

            model.addAttribute("currentUser", currentUser);
            model.addAttribute("findUser", find);

            return "user-arts";
        }

        ///  TODO: Можно сделать редирект на ПОЛЬЗОВАТЕЛЬ НЕ НАЙДЕН
        return "redirect:/";
    }

    @GetMapping("/{nickname}/{id}")
    public String productInfo(@PathVariable String nickname, @PathVariable Long id, Model model, Principal principal) {
        Art art = artService.getArtById(id);

        model.addAttribute("currentUser", artService.getUserByPrincipal(principal));

        model.addAttribute("art", art);
        model.addAttribute("author", userRepository.getUser_ByUserNickName(nickname));

        if (userRepository.getUser_ByUserNickName(nickname) != null) {
            if (userRepository.getUser_ByUserNickName(nickname).getUserCollection().getCollections_arts().contains(art)) {
                model.addAttribute("isContain", true);
                return "art-info";
            }
        }

        model.addAttribute("isContain", false);
        return "art-info";
    }

    @GetMapping("/addart")
    public String addart(Principal principal, Model model) {
        model.addAttribute("currentUser", artService.getUserByPrincipal(principal));
        return "add-art";
    }

    // Обработка POST-запросов
    @PostMapping("/addart")
    public String createProduct(@RequestParam("file") MultipartFile file, Art art, Principal principal) throws IOException {
        artService.addArt(principal, art, file);

        User user = artService.getUserByPrincipal(principal);
        return "redirect:/" + user.getUserNickName();
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Principal principal) {
        artService.deleteArt(principal, id);

        User user = artService.getUserByPrincipal(principal);
        return "redirect:/" + user.getUserNickName();
    }
}