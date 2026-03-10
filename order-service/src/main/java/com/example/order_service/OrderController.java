package com.example.order_service;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class OrderController {

    @GetMapping("/hello")
    public Map<String, Object> hello() {

        Map<String, Object> response = new HashMap<>();
        response.put("service", "order-service");
        response.put("message", "Order service is working");
        response.put("status", "success");

        return response;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getOrder(@PathVariable String id) {

        Map<String, Object> order = new HashMap<>();
        order.put("orderId", id);
        order.put("product", "Laptop");
        order.put("price", 1200);

        return order;
    }

    @GetMapping("")
    public ResponseEntity<String> getOrder1() {
        return ResponseEntity.ok("testing");
    }
}