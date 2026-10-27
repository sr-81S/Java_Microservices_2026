package com.embarkx.FirstSpring.services;

import com.embarkx.FirstSpring.dtos.CartItemRequest;
import com.embarkx.FirstSpring.models.CartItem;
import com.embarkx.FirstSpring.repository.CartItemRepositries;
import com.embarkx.FirstSpring.repository.ProductRepositries;
import com.embarkx.FirstSpring.repository.UserRepositries;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductRepositries productRepositries;
    private final UserRepositries userRepositries;
    private final CartItemRepositries cartItemRepositritries;

    public boolean addToCart(String userId, CartItemRequest request) {
        var user = userRepositries.findById(Long.valueOf(userId)).orElse(null);
        if (user == null) {
            return false;
        }

        var product = productRepositries.findById(request.getProductId()).orElse(null);
            if (product == null || product.getQuantity() < request.getQuantity()) {
                return false;
        }

        var existingCartItem = cartItemRepositritries.findByUserAndProduct(user, product);
        if (existingCartItem != null) {
            //update the quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            existingCartItem.setPrice(
                    existingCartItem.getPrice().add(
                            product.getPrice().multiply(
                                    BigDecimal.valueOf(request.getQuantity())
                            )
                    )
            );
            cartItemRepositritries.save(existingCartItem);
            return true;
        }

        var cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(request.getQuantity());
        cartItem.setPrice(
                product.getPrice().multiply(
                        BigDecimal.valueOf(request.getQuantity())
                )
        );
        cartItemRepositritries.save(cartItem);
        return true;
    }
//Delete Items from cart
    public boolean deleteItemFromCart(String userId, Long productId) {
        var user = userRepositries.findById(Long.valueOf(userId)).orElse(null);
        if (user == null) {
            return false;
        }
        var product = productRepositries.findById(productId).orElse(null);
        if (product == null) {
            return false;
        }

        var existingCartItem = cartItemRepositritries.findByUserAndProduct(user, product);
        if (existingCartItem == null) {
            return false;
        }
        cartItemRepositritries.delete(existingCartItem);
        return true;
    }

    public List<CartItem> getCartForUser(String userId) {
        System.out.println("before userId: " + userId);
        var user = userRepositries.findById(Long.valueOf(userId)).orElse(null);
        if (user != null) {
            var cartItems = cartItemRepositritries.findAllByUser(user.getId());
            return  cartItems;
        }
        return null;
    }
}
