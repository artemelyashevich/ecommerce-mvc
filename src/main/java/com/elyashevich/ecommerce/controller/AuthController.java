package com.elyashevich.ecommerce.controller;

import com.elyashevich.ecommerce.dto.auth.LoginDto;
import com.elyashevich.ecommerce.dto.auth.RegisterDto;
import com.elyashevich.ecommerce.exception.PasswordMismatchException;
import com.elyashevich.ecommerce.mapper.LoginMapper;
import com.elyashevich.ecommerce.mapper.RegisterMapper;
import com.elyashevich.ecommerce.service.AuthService;
import com.elyashevich.ecommerce.util.ValidationHandlerUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RegisterMapper registerMapper;
    private final LoginMapper loginMapper;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(
            final @Valid @ModelAttribute("user") RegisterDto authDto,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            ValidationHandlerUtil.handleValidation(model, bindingResult);
            return "redirect:/auth/register";
        }
        try {
            this.authService.register(this.registerMapper.toEntity(authDto));
        }  catch (Exception e) {
            model.addAttribute("errors", List.of(e.getMessage()));
            return "redirect:/register";
        }
        return "redirect:/register";
    }

    @PostMapping("/login")
    public String login(
            final @Valid @ModelAttribute("user") LoginDto authDto,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            ValidationHandlerUtil.handleValidation(model, bindingResult);
            return "redirect:/auth/login";
        }
        try {
            this.authService.login(this.loginMapper.toEntity(authDto));
        } catch (Exception e) {
            model.addAttribute("errors", List.of(e.getMessage()));
            return "redirect:/auth/login";
        }
            return "redirect:/auth/login";
    }
}
