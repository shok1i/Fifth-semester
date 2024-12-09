package com.cursework.kuroi.services;

import com.cursework.kuroi.models.Art;
import com.cursework.kuroi.models.User;
import com.cursework.kuroi.models.Order;
import com.cursework.kuroi.repositories.ArtRepository;
import com.cursework.kuroi.repositories.UserRepository;
import com.cursework.kuroi.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ArtRepository artRepository;
    private final UserRepository userRepository;

    public void makeOrder(User user, List<Art> artsOrder, Long price) {
        Order order = new Order();

        order.setOrderStatus("Ожидание оплаты");
        order.setArts(artsOrder);
        order.setUser(user);
        order.setPrice(price);

        List <Order> userOrders = user.getOrders();
        userOrders.add(order);
        user.setOrders(userOrders);
        log.warn("FuckDebug in service {}", user.getOrders().size());

        orderRepository.save(order);
    }

    public void changeOrderStatus(Order order, String status) {
        order.setOrderStatus(status);
    }

    public List<Order> getByUser(User user) {
        return orderRepository.getByUser(user);
    }
}
