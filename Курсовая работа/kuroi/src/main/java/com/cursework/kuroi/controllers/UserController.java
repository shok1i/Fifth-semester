package com.cursework.kuroi.controllers;

import com.cursework.kuroi.models.Art;
import com.cursework.kuroi.models.User;
import com.cursework.kuroi.repositories.ArtRepository;
import com.cursework.kuroi.repositories.ImageRepository;
import com.cursework.kuroi.repositories.UserRepository;
import com.cursework.kuroi.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final ArtRepository artRepository;

    // Обработка GET-запросов
    @GetMapping("/")
    public String home(Principal principal, Model model) {
        model.addAttribute("currentUser", userService.getUserByPrinciple(principal));
        return "home";
    }

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("currentUser", userService.getUserByPrinciple(principal));
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Principal principal, Model model) {
        model.addAttribute("currentUser", userService.getUserByPrinciple(principal));
        return "registration";
    }

    @GetMapping("/account")
    public String account(Principal principal, Model model) {
        model.addAttribute("currentUser", userService.getUserByPrinciple(principal));
        return "account";
    }

    @GetMapping("/{nickname}")
    public String userProducts(@PathVariable String nickname, Principal principal, Model model) {
        User currentUser = userService.getUserByPrinciple(principal);

        if (userRepository.findByUserNickName(nickname) != null) {
            User find = userRepository.findByUserNickName(nickname);
            List<Art> findArts = artRepository.findByAuthorId(find.getId());

            model.addAttribute("currentUser", currentUser);
            model.addAttribute("findUser", find);
            model.addAttribute("arts", findArts);
            return "user-arts";
        }
        ///  TODO: Можно сделать редирект на ПОЛЬЗОВАТЕЛЬ НЕ НАЙДЕН
        return "redirect:/";
    }

    @GetMapping("/account/edit")
    public String accountEdit(Principal principal, Model model) {
        model.addAttribute("currentUser", userService.getUserByPrinciple(principal));
        return "account-edit";
    }

    // Обработка POST-запросов
    // Todo:
    //  Сделать так что бы после регистрации у нас был автоматический вход
    //  Сделать возвращаемое значение метода addUser на Long|String и т.п. что бы ловить код ошибки при регистрации (такой никнейм уже есть такой емаил уже есть) ТОЖЕ САМОЕ СДЕЛАТЬ В ЛОГИНЕ
    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.addUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getUserEmail() + " уже существует");
            return "registration";
        }

        return "redirect:/";
    }

    @PostMapping("/account/edit")
    public String editUser(Principal principal, String userBIO, String userNickName, MultipartFile file, Model model) throws IOException {
        log.info("String userBIO {}, String userNickName {}", userBIO, userNickName);
        model.addAttribute("currentUser", userService.getUserByPrinciple(principal));

        if (userService.changeUserInformation(principal, userBIO, userNickName, file)) {
            return "redirect:/account";
        }

        model.addAttribute("errorMessage", "ERROR");
        return "account-edit";
    }
}
