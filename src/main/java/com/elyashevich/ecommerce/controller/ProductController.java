package com.elyashevich.ecommerce.controller;

import com.elyashevich.ecommerce.dto.ProductDto;
import com.elyashevich.ecommerce.mapper.ProductMapper;
import com.elyashevich.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public String products(Model model) {
        var products = this.productService.findAll();
        model.addAttribute("products", this.productMapper.toDto(products));
        return "product/products";
    }

    @GetMapping("/{id}")
    public String product(@PathVariable Long id, Model model) {
        var product = this.productService.findById(id);
        model.addAttribute("product", this.productMapper.toDto(product));
        return "product/product";
    }

    @GetMapping("/create")
    public String create() {
        return "product/create-product";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        var product = this.productService.findById(id);
        model.addAttribute("product", this.productMapper.toDto(product));
        return "product/edit-product";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("productDto") ProductDto productDto) {
        var product  = this.productService.create(productMapper.toEntity(productDto));
        return "redirect:/products/%d".formatted(product.getId());
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, @Valid @ModelAttribute("productDto") ProductDto productDto) {
        this.productService.update(id, this.productMapper.toEntity(productDto));
        return "redirect:/products/%d".formatted(id);
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        this.productService.delete(id);
        return "redirect:/products";
    }
}
