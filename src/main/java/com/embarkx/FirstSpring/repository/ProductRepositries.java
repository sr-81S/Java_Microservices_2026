package com.embarkx.FirstSpring.repository;

import com.embarkx.FirstSpring.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepositries extends JpaRepository<Product, Long> {

    List<Product> findByActiveTrue();

    @Query("SELECT p FROM product_table p WHERE p.active = true AND p.quantity > 0 AND (LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Product> searchByKeyword(@Param("keyword") String keyword);
}
