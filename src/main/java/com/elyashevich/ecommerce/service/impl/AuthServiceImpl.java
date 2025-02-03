package com.elyashevich.ecommerce.service.impl;

import com.elyashevich.ecommerce.entity.User;
import com.elyashevich.ecommerce.exception.PasswordMismatchException;
import com.elyashevich.ecommerce.service.AuthService;
import com.elyashevich.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    public static final String PASSWORD_MISMATCH_EXCEPTION_TEMPLATE = "Password mismatch";
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(final User candidate) {
        log.info("Registering user: {}", candidate);

        this.userService.create(candidate);

        log.info("Registered user: {}", candidate);
    }

    @Override
    public void login(final User candidate) {
        log.info("Authenticating user: {}", candidate);

        var user = this.userService.findByEmail(candidate.getEmail());

        if (!this.passwordEncoder.matches(candidate.getPassword(), user.getPassword())) {
            log.warn(PASSWORD_MISMATCH_EXCEPTION_TEMPLATE);

            throw new PasswordMismatchException(PASSWORD_MISMATCH_EXCEPTION_TEMPLATE);
        }

        log.info("Authenticated user: {}", user);
    }
}
