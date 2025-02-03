package com.elyashevich.ecommerce.service.impl;

import com.elyashevich.ecommerce.dto.CartDto;
import com.elyashevich.ecommerce.entity.Cart;
import com.elyashevich.ecommerce.exception.ResourceNotFoundException;
import com.elyashevich.ecommerce.repository.CartRepository;
import com.elyashevich.ecommerce.service.CartService;
import com.elyashevich.ecommerce.service.ProductService;
import com.elyashevich.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    public static final String CART_WITH_ID_WAS_NOT_FOUND_EXCEPTION_TEMPLATE = "Cart with id '%s' was not found.";

    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;

    @Override
    public List<Cart> findAll() {
        log.info("Get carts");

        var cart = this.cartRepository.findAll();

        log.info("Cart found");
        return cart;
    }

    @Override
    public Cart findById(final Long id) {
        log.info("Get cart by id");

        var cart = this.cartRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(CART_WITH_ID_WAS_NOT_FOUND_EXCEPTION_TEMPLATE.formatted(id))
        );

        log.info("Cart found");
        return cart;
    }

    @Transactional
    @Override
    public void addCart(final CartDto cartDto) {
        log.info("Add cart");

        var product = this.productService.findById(cartDto.productId());
        var user = this.userService.findByUsername(cartDto.username());

        var cart = Cart.builder()
                .user(user)
                .product(product)
                .build();
        cart.setUser(user);
        cart.setProduct(product);

        this.cartRepository.save(cart);

        log.info("Cart added");
    }

    @Transactional
    @Override
    public void remove(final Long id) {
        log.info("Remove cart");

        var candidate = this.findById(id);
        this.cartRepository.delete(candidate);

        log.info("Cart removed");
    }
}
