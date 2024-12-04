package com.cursework.kuroi.controllers;

import com.cursework.kuroi.models.User;
import com.cursework.kuroi.models.enums.Role;
import com.cursework.kuroi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    // Обработка GET-запросов
    @GetMapping("/admin")
    public String adminPage(Model model, Principal principal) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("currentUser", userService.getUSerByPrinciple(principal));
        return "admin";
    }

    @GetMapping("/admin/edit/{user}")
    public String changeUserRoles(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("currentUser", userService.getUSerByPrinciple(principal));
        model.addAttribute("roles", Role.values());
        return "user-change-roles";
    }

    // Обработка POST-запросов
    @PostMapping("/admin/edit")
    public String changeUserRoles(@RequestParam("user") User user, @RequestParam Map<String, String> form) {
        userService.changeUserRoles(user, form);
        return "redirect:/admin" + user.getId();
    }

    @PostMapping("/admin/ban")
    public String banUser(@RequestParam("id") Long id){
        userService.changeUserBanStatus(id);
        return "redirect:/admin";
    }
}
