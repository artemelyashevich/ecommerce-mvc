package com.elyashevich.ecommerce.service.impl;

import com.elyashevich.ecommerce.entity.User;
import com.elyashevich.ecommerce.exception.PasswordMismatchException;
import com.elyashevich.ecommerce.service.AuthService;
import com.elyashevich.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    public static final String PASSWORD_MISMATCH_EXCEPTION_TEMPLATE = "Password mismatch";
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(final User candidate) {
        this.userService.create(candidate);
    }

    @Override
    public void login(final User candidate) {
        var user = this.userService.findByEmail(candidate.getEmail());
        if (!this.passwordEncoder.matches(candidate.getPassword(), user.getPassword())) {
            throw new PasswordMismatchException(PASSWORD_MISMATCH_EXCEPTION_TEMPLATE);
        }
    }
}
