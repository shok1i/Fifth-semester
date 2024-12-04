package com.cursework.kuroi.controllers;

import com.cursework.kuroi.models.Art;
import com.cursework.kuroi.models.User;
import com.cursework.kuroi.services.ArtService;
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

@Controller
@RequiredArgsConstructor
public class ArtController {
    private final ArtService artService;

    // Обработка GET-запросов
    @GetMapping("/gallery")
    public String products(@RequestParam(name = "searchWord", required = false) String title, Principal principal, Model model) {
        model.addAttribute("products", artService.getArtsByKeyword(title));
        model.addAttribute("user", artService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", title);
        return "gallery";
    }

    @GetMapping("/{nickname}/{id}")
    public String productInfo(@PathVariable String nickname, @PathVariable Long id, Model model, Principal principal) {
        Art art = artService.getArtById(id);
        model.addAttribute("user", artService.getUserByPrincipal(principal));
        model.addAttribute("art", art);
        model.addAttribute("image", art.getImage());
        model.addAttribute("authorProduct", nickname);
        return "art-info";
    }

    @GetMapping("/{nickname}")
    public String userProducts(@PathVariable String nickname, Principal principal, Model model) {
        User user = artService.getUserByPrincipal(principal);
        model.addAttribute("currentUser", user);
        model.addAttribute("arts", user.getArts());
        return "user-arts";
    }

    @GetMapping("/addart")
    public String addart(Principal principal, Model model) {
        User user = artService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "addart";
    }

    // Обработка POST-запросов
    @PostMapping("/addart")
    public String createProduct(@RequestParam("file") MultipartFile file, Art product, Principal principal) throws IOException {
        artService.addArt(principal, product, file);

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