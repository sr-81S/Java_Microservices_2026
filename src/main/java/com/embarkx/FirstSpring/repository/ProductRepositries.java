package com.embarkx.FirstSpring.repository;

import com.embarkx.FirstSpring.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepositries extends JpaRepository<Product, Long> {

    List<Product> findByActiveTrue();
}
