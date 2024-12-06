package com.cursework.kuroi.controllers;

import com.cursework.kuroi.models.User;
import com.cursework.kuroi.models.enums.Role;
import com.cursework.kuroi.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    // Обработка GET-запросов
    @GetMapping("/admin_panel")
    public String adminPage(@RequestParam(name = "searchWord", required = false) String title, Model model, Principal principal) {
        if (title != null)
            if (title.isEmpty())
                title = null;

        model.addAttribute("users", userService.getUserByKeyWord(title));
        model.addAttribute("searchWord", title);

        model.addAttribute("currentUser", userService.getUserByPrinciple(principal));
        model.addAttribute("roles", Role.values());

        return "admin-panel";
    }

    // Обработка POST-запросов
    @PostMapping("/admin_panel/edit")
    public String changeUserRoles(@RequestParam("editUser") String editUserEmail, @RequestParam("newRole") String newRole) {
        userService.changeUserRoles(editUserEmail, newRole);
        return "redirect:/admin_panel";
    }

    @PostMapping("/admin_panel/ban")
    public String banUser(@RequestParam("editUser") Long id){
        userService.changeUserBanStatus(id);
        return "redirect:/admin_panel";
    }
}
