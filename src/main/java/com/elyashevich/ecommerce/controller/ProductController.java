package com.elyashevich.ecommerce.controller;

import com.elyashevich.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String products(final Model model) {
        var products = this.productService.findAll();
        model.addAttribute("products", products);
        return "product/products";
    }

    @GetMapping("/{id}")
    public String product(final @PathVariable Long id, final Model model) {
        var product = this.productService.findById(id);
        model.addAttribute("product", product);
        return "product/product";
    }
}
