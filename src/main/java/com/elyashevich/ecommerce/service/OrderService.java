package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.entity.Order;

public interface OrderService {

    void makeOrder(final Order order);
}
