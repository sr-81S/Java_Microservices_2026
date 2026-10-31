package com.embarkx.FirstSpring.controllers;

import com.embarkx.FirstSpring.dtos.OrderRequest;
import com.embarkx.FirstSpring.dtos.OrderResponce;
import com.embarkx.FirstSpring.repository.OrderRepositries;
import com.embarkx.FirstSpring.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    //create order
    @PostMapping
    public ResponseEntity<OrderResponce> createOrder(
            @RequestHeader("X-User-ID") String userId) {
        OrderResponce orderResponce = orderService.createOrder(userId);
        if (orderResponce != null) {
            return ResponseEntity.ok(orderResponce);
        }
        return ResponseEntity.badRequest().build();
    }
}
