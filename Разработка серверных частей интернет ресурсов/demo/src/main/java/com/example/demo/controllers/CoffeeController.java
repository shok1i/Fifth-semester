package com.example.demo.controllers;

import com.example.demo.services.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CoffeeController {
    private final CoffeeService coffeeService;

    @GetMapping("/coffee")
    public String coffee(Model model) {
        model.addAttribute("coffees", coffeeService.findAll());
        return "coffee";
    }
}
