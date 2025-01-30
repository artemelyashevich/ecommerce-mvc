package com.elyashevich.ecommerce.controller;

import com.elyashevich.ecommerce.dto.ProductDto;
import com.elyashevich.ecommerce.mapper.CategoryMapper;
import com.elyashevich.ecommerce.mapper.ProductMapper;
import com.elyashevich.ecommerce.service.CategoryService;
import com.elyashevich.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    @GetMapping
    public String products(final Model model) {
        var products = this.productService.findAll();
        model.addAttribute("products", this.productMapper.toDto(products));
        return "product/products";
    }

    @GetMapping("/{id}")
    public String product(final @PathVariable Long id, final Model model) {
        var product = this.productService.findById(id);
        model.addAttribute("product", this.productMapper.toDto(product));
        return "product/product";
    }
}
