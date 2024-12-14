package com.example.demo.services;

import com.example.demo.models.Coffee;
import com.example.demo.repositories.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public List<Coffee> findAll() {
        return coffeeRepository.findAll();
    }
}
