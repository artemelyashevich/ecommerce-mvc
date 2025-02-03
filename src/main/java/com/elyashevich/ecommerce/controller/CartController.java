package com.elyashevich.ecommerce.controller;

import com.elyashevich.ecommerce.dto.CartDto;
import com.elyashevich.ecommerce.entity.Order;
import com.elyashevich.ecommerce.entity.User;
import com.elyashevich.ecommerce.service.CartService;
import com.elyashevich.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final OrderService orderService;

    @GetMapping
    public String getCart(final Model model) {
        var cart = this.cartService.findAll();
        model.addAttribute("cart", cart);
        return "cart/cart";
    }

    @PostMapping("/{productId}")
    public String postCart(@PathVariable("productId") final Long productId) {
        var userName = SecurityContextHolder.getContext().getAuthentication().getName();
        this.cartService.addCart(new CartDto(productId, userName));
        return "cart/cart";
    }

    @PostMapping("/delete/{cartId}")
    public String deleteCart(@PathVariable("cartId") final Long cartId) {
        this.cartService.remove(cartId);
        return "cart/cart";
    }

    @PostMapping("/order")
    public String order() {
        var userName = SecurityContextHolder.getContext().getAuthentication().getName();
        this.orderService.makeOrder(Order.builder()
                        .user(User.builder().username(userName).build())
                .build());
        return "redirect:/cart";
    }
}
