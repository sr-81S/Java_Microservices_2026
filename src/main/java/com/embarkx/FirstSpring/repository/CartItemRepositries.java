package com.embarkx.FirstSpring.repository;

import com.embarkx.FirstSpring.models.CartItem;
import com.embarkx.FirstSpring.models.Product;
import com.embarkx.FirstSpring.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepositries extends CrudRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);


    List<CartItem> findAllByUser(User user);

    @Modifying
    @Transactional
    void deleteByUser(User user);
}
