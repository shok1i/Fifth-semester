package com.cursework.kuroi.controllers;

import com.cursework.kuroi.models.User;
import com.cursework.kuroi.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

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

    @GetMapping("/account/edit")
    public String accountEdit(Principal principal, Model model) {
        model.addAttribute("currentUser", userService.getUserByPrinciple(principal));
        return "account-edit";
    }

    @GetMapping("/current-user")
    public User currentUser(Principal principal, Model model) {
        model.addAttribute("currentUser", userService.getUserByPrinciple(principal));
        return userService.getUserByPrinciple(principal);
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model, HttpServletRequest request) {
        if (!userService.addUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getUserEmail() + " уже существует");
            return "registration";
        }

        return "redirect:/login";
    }

    @PostMapping("/account/edit")
    public String editUser(Principal principal, String userBIO, String userNickName, MultipartFile userAvatar, Model model, MultipartFile userBanner) throws IOException {
        model.addAttribute("currentUser", userService.getUserByPrinciple(principal));

        if (userService.changeUserInformation(principal, userBIO, userNickName, userAvatar, userBanner)) {
            return "redirect:/account";
        }

        model.addAttribute("errorMessage", "ERROR");
        return "account-edit";
    }
}
