package com.embarkx.FirstSpring.repository;

import com.embarkx.FirstSpring.models.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepositries extends CrudRepository<CartItem, Long> {
}
