package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.dto.CartDto;
import com.elyashevich.ecommerce.entity.Cart;

import java.util.List;

/**
 * The CartService interface provides methods for managing shopping carts
 * in the e-commerce application.
 */
public interface CartService {

    /**
     * Retrieves all shopping carts in the system.
     *
     * @return a list of all carts
     */
    List<Cart> findAll();

    /**
     * Retrieves a shopping cart by its unique identifier.
     *
     * @param id the unique identifier of the cart
     * @return the cart with the specified id
     */
    Cart findById(final Long id);

    /**
     * Adds a new shopping cart to the system.
     *
     * @param cart the cart data transfer object containing cart details
     */
    void addCart(final CartDto cart);

    /**
     * Removes a shopping cart from the system by its unique identifier.
     *
     * @param id the unique identifier of the cart to be removed
     */
    void remove(final Long id);

    /**
     * Removes all shopping carts associated with a specific user.
     *
     * @param userId the unique identifier of the user
     */
    void removeAll(final Long userId);

    /**
     * Retrieves all shopping carts associated with a specific user.
     *
     * @param id the unique identifier of the user
     * @return a list of all carts associated with the user
     */
    List<Cart> findAllByUserId(final Long id);
}
