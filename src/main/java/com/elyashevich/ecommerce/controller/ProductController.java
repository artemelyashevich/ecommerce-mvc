package com.elyashevich.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @GetMapping
    public String products(Model model) {
        return "products";
    }
}
