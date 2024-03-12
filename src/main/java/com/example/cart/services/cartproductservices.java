package com.example.cart.services;

import com.example.cart.dtos.productdto;

import com.example.cart.models.product;

import java.util.List;

public interface cartproductservices {
    List<product> getAllProducts();

    product getSingleProduct(Long id);

    productdto createProduct(product product);
    List<String> getCategory();

    void deleteProduct(Long id);

    void updateProduct(product product);

    List<product> getProductByCategory(String CategoryN);
}