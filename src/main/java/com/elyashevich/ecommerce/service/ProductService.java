package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(final Long id);

    Product create(final Product product);

    Product update(final Long id, final Product product);

    void delete(final Long id);
}
