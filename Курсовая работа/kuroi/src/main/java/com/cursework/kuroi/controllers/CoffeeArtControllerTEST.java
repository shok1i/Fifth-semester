package com.cursework.kuroi.controllers;

import com.cursework.kuroi.models.CoffeeArtTEST;
import com.cursework.kuroi.services.CoffeeArtServiceTEST;
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
public class CoffeeArtControllerTEST {
    private final CoffeeArtServiceTEST coffeeArtService;

    @GetMapping("/gallery")
    public String coffeeArts(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("coffee_arts", coffeeArtService.listCoffeeArt(title));
        model.addAttribute("user", coffeeArtService.getUserByPrinciple(principal));
        return "gallery";
    }

    @GetMapping("/{username}/{id}")
    public String ArtInfo(@PathVariable String username, @PathVariable Long id, Model model) {
        CoffeeArtTEST coffeeArt = coffeeArtService.getCoffeeArtById(id);
        model.addAttribute("coffee_art", coffeeArt);
        model.addAttribute("username", username);
        return "art-info";
    }

    @PostMapping("/addart")
    public String createArt(@RequestParam("file") MultipartFile file, CoffeeArtTEST coffeeArt, Principal principal) throws IOException {
        coffeeArtService.saveCoffeeArt(principal, coffeeArt, file);
        return "redirect:/gallery";
    }

    @PostMapping("/art/{username}/{id}")
    public String deleteArt(@PathVariable String username, @PathVariable Long id, Principal principal) {
        coffeeArtService.deleteCoffeeArt(id);
        return "redirect:/gallery";
    }
}
