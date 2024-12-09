package com.cursework.kuroi.controllers;

import com.cursework.kuroi.models.Art;
import com.cursework.kuroi.models.User;
import com.cursework.kuroi.services.ArtService;
import com.cursework.kuroi.services.UserService;
import com.cursework.kuroi.services._OrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class _OrderController {
    private final UserService userService;
    private final ArtService artService;
    private final _OrderService orderService;

    @GetMapping("/order/list")
    public String orderList(Model model,  Principal principal) {
        User currentUser = userService.getUserByPrinciple(principal);

        log.warn("FuckDebug in GET: {}", currentUser.getOrders().size());

        model.addAttribute("orderList", currentUser.getOrders());
        model.addAttribute("currentUser", currentUser);
        return "order-list";
    }

    @GetMapping("/order/{orderID}")
    public String orderDetails(Model model,  Principal principal) {


        model.addAttribute("currentUser", userService.getUserByPrinciple(principal));
        return "order-details";
    }

    @GetMapping("/order/make")
    public String makeOrder(Model model,  Principal principal) {
        User currentUser = userService.getUserByPrinciple(principal);

        model.addAttribute("orderList", orderService.getByUser(currentUser));

        model.addAttribute("currentUser", currentUser);
        return "order-make";
    }

    @PostMapping("/api/make-order")
    public ResponseEntity<String> makeOrder(@RequestBody OrderRequest  orderBody){
        Long userId = orderBody.getUser();
        List<Long> artIds = orderBody.getArts();
        Long price = orderBody.getPrice();

        User user = userService.getUserById(userId);
        List<Art> arts = artService.findArtsByIds(artIds);

        orderService.makeOrder(user, arts, price);

        log.warn("FuckDebug in POST: {}", user.getOrders().size());

        return ResponseEntity.ok("Заказ сформирован успешно");
    }
}

// Класс для разбивки JSON
class OrderRequest {
    private Long user;
    private Long price;
    private List<Long> arts;

    // Геттеры и сеттеры
    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<Long> getArts() {
        return arts;
    }

    public void setArts(List<Long> arts) {
        this.arts = arts;
    }
}
