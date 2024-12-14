package com.example.demo.controllers;

import com.example.demo.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order")
    public String order(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "order";
    }
}
