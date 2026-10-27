package com.embarkx.FirstSpring.controllers;

import com.embarkx.FirstSpring.dtos.CartItemRequest;
import com.embarkx.FirstSpring.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<?> addToCart(
            @RequestHeader("X-User-ID") String userId,
            @RequestBody CartItemRequest request
    ){
        var res = cartService.addToCart(userId, request);
        if(!res){
            return ResponseEntity.status(400).body("Invalid request");
        }
        return ResponseEntity.status(201).body("Item added to cart");
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<?> removeFromCart(
            @RequestHeader("X-User-ID") String userId, @PathVariable Long productId
    ){
        var res = cartService.deleteItemFromCart(userId, productId);
        if(!res) {
            return ResponseEntity.status(400).body("Invalid request");
        }
        return ResponseEntity.status(200).body("Item deleted from cart");
    }
}
