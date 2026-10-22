package com.embarkx.FirstSpring.services;

import com.embarkx.FirstSpring.dtos.ProductRequest;
import com.embarkx.FirstSpring.dtos.ProductResponce;
import com.embarkx.FirstSpring.models.Product;
import com.embarkx.FirstSpring.repository.ProductRepositries;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServices {

    private final ProductRepositries  productRepositries;

    public ProductResponce createProduct(ProductRequest productRequest) {

        var product = productRepositries.save(convertToEntity(productRequest));
        return convertToResponce(product);
    }

    private ProductResponce convertToResponce(Product product) {
        ProductResponce productResponce = new ProductResponce();
        productResponce.setId(product.getId());
        productResponce.setName(product.getName());
        productResponce.setDescription(product.getDescription());
        productResponce.setPrice(product.getPrice());
        productResponce.setQuantity(product.getQuantity());
        productResponce.setImageUrl(product.getImageUrl());
        productResponce.setCategory(product.getCategory());
        productResponce.setActive(product.getActive());
        return productResponce;
    }

    //convert productRequest to Product entity
    public Product convertToEntity(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setImageUrl(productRequest.getImageUrl());
        product.setCategory(productRequest.getCategory());
        return product;
    }

    public ProductResponce updateProduct(Long id, ProductRequest productRequest) {
        // Implementation for updating a product
        var product = productRepositries.findById(id).orElse(null);
        // Update the product fields
        if (product != null) {
            product.setName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setQuantity(productRequest.getQuantity());
            product.setImageUrl(productRequest.getImageUrl());
            product.setCategory(productRequest.getCategory());
        }else {
            return null;
        }

        productRepositries.save(product);
        return convertToResponce(product);
    }

    public List<ProductResponce> getAllProducts() {
        return productRepositries.findByActiveTrue().stream()
                .map(this::convertToResponce)
                .collect(java.util.stream.Collectors.toList());
    }

    public ProductResponce getProductById(Long id) {
        return productRepositries.findById(id).map(this::convertToResponce).orElse(null);
    }

    public boolean deleteProduct(Long id) {
        return productRepositries.findById(id).map(product -> {
            product.setActive(false);
            productRepositries.save(product);
            return true;
        }).orElse(false);
    }

    public List<ProductResponce> searchProducts(String keyword) {
        return productRepositries.searchByKeyword(keyword).stream()
                .map(this::convertToResponce)
                .collect(Collectors.toList());
    }
}
