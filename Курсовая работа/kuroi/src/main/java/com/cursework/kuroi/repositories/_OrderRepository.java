package com.cursework.kuroi.repositories;

import com.cursework.kuroi.models.User;
import com.cursework.kuroi.models._Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  _OrderRepository extends JpaRepository<_Order, Long> {

    List<_Order> getByUser(User user);
}
