package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Long id);

    Product create(Product product);

    Product update(Long id, Product product);

    void delete(Long id);
}
