package com.cursework.kuroi.controllers;

import com.cursework.kuroi.models.User;
import com.cursework.kuroi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // Обработка GET-запросов
    @GetMapping("/")
    public String home (Principal principal, Model model) {
        model.addAttribute("currentUser", userService.getUSerByPrinciple(principal));
        return "home";
    }

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("currentUser", userService.getUSerByPrinciple(principal));
        return "login";
    }

    @GetMapping("/account")
    public String account(Principal principal, Model model) {
        model.addAttribute("currentUser", userService.getUSerByPrinciple(principal));
        return "account";
    }

    @GetMapping("/registration")
    public String registration(Principal principal, Model model) {
        model.addAttribute("currentUser", userService.getUSerByPrinciple(principal));
        return "registration";
    }

    // Todo: Тут логика в том что мы из likes будем получать Art и выводить их
    @GetMapping("/my")
    public String my(Principal principal, Model model) {
        User currentUser = userService.getUSerByPrinciple(principal);
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("likes", currentUser.getLikes());
        return "user-info";
    }

    @GetMapping("/{user}")
    public String user(Principal principal, @PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("currentUser", userService.getUSerByPrinciple(principal));
        model.addAttribute("products", user.getArts());
        return "user-info";
    }

    // Обработка POST-запросов
    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.addUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getUserEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }


}
