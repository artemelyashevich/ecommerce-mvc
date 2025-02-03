package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.entity.Product;

import java.util.List;

/**
 * The ProductService interface provides methods for managing products in the e-commerce system.
 */
public interface ProductService {

    /**
     * Finds all products.
     *
     * @return a list of all products
     */
    List<Product> findAll();

    /**
     * Finds a product by its ID.
     *
     * @param id the ID of the product to find
     * @return the found product, or null if not found
     */
    Product findById(final Long id);

    /**
     * Creates a new product.
     *
     * @param product the product to create
     * @return the created product
     */
    Product create(final Product product);

    /**
     * Updates an existing product.
     *
     * @param id the ID of the product to update
     * @param product the updated product information
     * @return the updated product
     */
    Product update(final Long id, final Product product);

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     */
    void delete(final Long id);
}
