package com.embarkx.FirstSpring.controllers;

import com.embarkx.FirstSpring.dtos.ProductRequest;
import com.embarkx.FirstSpring.dtos.ProductResponce;
import com.embarkx.FirstSpring.services.ProductServices;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor

public class ProductController {

    private final ProductServices productService;

    @GetMapping
    public ResponseEntity<List<ProductResponce>> getAllProducts() {
        var allProduct = productService.getAllProducts();
        return ResponseEntity.ok(allProduct);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponce> getProductById(@PathVariable Long id)    {
        var product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductResponce> createProduct(@RequestBody ProductRequest productRequest){
        return ResponseEntity.status(201).body(productService.createProduct(productRequest));
    }

    //update the product
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponce> updateProduct(@PathVariable Long id,
                                                         @RequestBody ProductRequest productRequest){
        // Implementation for updating a product
        var product = productService.updateProduct(id, productRequest);
        if(product != null){
            return ResponseEntity.status(200).body(product);
        }
        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        var product = productService.deleteProduct(id);
        return product ?  ResponseEntity.status(204).body(null) : ResponseEntity.status(404).build();
    }
}
