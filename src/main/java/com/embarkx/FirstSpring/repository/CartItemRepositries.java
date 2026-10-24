package com.embarkx.FirstSpring.repository;

import com.embarkx.FirstSpring.models.CartItem;
import com.embarkx.FirstSpring.models.Product;
import com.embarkx.FirstSpring.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepositries extends CrudRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);
}
