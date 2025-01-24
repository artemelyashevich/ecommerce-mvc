package com.elyashevich.ecommerce.service.impl;

import com.elyashevich.ecommerce.entity.Role;
import com.elyashevich.ecommerce.entity.User;
import com.elyashevich.ecommerce.exception.PasswordMismatchException;
import com.elyashevich.ecommerce.exception.ResourceNotFoundException;
import com.elyashevich.ecommerce.repository.UserRepository;
import com.elyashevich.ecommerce.service.AuthService;
import com.elyashevich.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(User user) {
        this.userService.create(user);
    }

    @Override
    public void login(User candidate) {
        var user = this.userService.findByEmail(candidate.getEmail());
        if (!this.passwordEncoder.matches(candidate.getPassword(), user.getPassword())) {
            throw new PasswordMismatchException("Password mismatch");
        }
    }
}
