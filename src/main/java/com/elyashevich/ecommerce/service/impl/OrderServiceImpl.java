package com.elyashevich.ecommerce.service.impl;

import com.elyashevich.ecommerce.entity.Cart;
import com.elyashevich.ecommerce.entity.Order;
import com.elyashevich.ecommerce.entity.Product;
import com.elyashevich.ecommerce.repository.OrderRepository;
import com.elyashevich.ecommerce.service.CartService;
import com.elyashevich.ecommerce.service.OrderService;
import com.elyashevich.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserService userService;

    @Transactional
    @Override
    public void makeOrder(Order order) {
        var user = this.userService.findByUsername(order.getUser().getUsername());
        var cart = this.cartService.findAllByUserId(user.getId());
        order.setUser(user);
        order.setTotalAmount(
                cart.stream()
                        .map(Cart::getProduct)
                        .mapToDouble(Product::getPrice)
                        .sum()
        );
        this.cartService.removeAll(order.getUser().getId());
        this.orderRepository.save(order);
    }
}
