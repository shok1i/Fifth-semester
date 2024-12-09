package com.cursework.kuroi.repositories;

import com.cursework.kuroi.models.User;
import com.cursework.kuroi.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getByUser(User user);
}
