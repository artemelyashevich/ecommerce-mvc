package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.entity.Order;

/**
 * The OrderService interface provides methods for managing orders
 * in the e-commerce application.
 */
public interface OrderService {

    /**
     * Processes an order in the e-commerce system.
     *
     * @param order the order to be processed
     */
    void makeOrder(final Order order);
}
