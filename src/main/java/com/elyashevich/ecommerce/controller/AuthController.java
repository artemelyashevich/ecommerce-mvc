package com.elyashevich.ecommerce.controller;

import com.elyashevich.ecommerce.dto.auth.LoginDto;
import com.elyashevich.ecommerce.dto.auth.RegisterDto;
import com.elyashevich.ecommerce.mapper.LoginMapper;
import com.elyashevich.ecommerce.mapper.RegisterMapper;
import com.elyashevich.ecommerce.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RegisterMapper registerMapper;
    private final LoginMapper loginMapper;

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(){
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(final @Valid @ModelAttribute("user") RegisterDto authDto) {
        this.authService.register(this.registerMapper.toEntity(authDto));
        return "redirect:/products";
    }

    @PostMapping("/login")
    public String login(final @Valid @ModelAttribute("user") LoginDto authDto) {
        this.authService.login(this.loginMapper.toEntity(authDto));
        return "redirect:/products";
    }
}
