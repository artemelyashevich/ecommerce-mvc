package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.dto.CartDto;
import com.elyashevich.ecommerce.entity.Cart;

import java.util.List;

public interface CartService {

    List<Cart> findAll();

    Cart findById(final Long id);

    void addCart(final CartDto cart);

    void remove(final Long id);
}
