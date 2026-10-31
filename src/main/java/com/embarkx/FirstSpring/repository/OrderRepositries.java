package com.embarkx.FirstSpring.repository;

import com.embarkx.FirstSpring.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositries extends JpaRepository<Order, Long> {
}
