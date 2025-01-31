package com.elyashevich.ecommerce.service.impl;

import com.elyashevich.ecommerce.entity.Role;
import com.elyashevich.ecommerce.entity.User;
import com.elyashevich.ecommerce.exception.ResourceNotFoundException;
import com.elyashevich.ecommerce.repository.UserRepository;
import com.elyashevich.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(final String email) {
        return this.userRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("User with email %s was not found".formatted(email))
        );
    }

    @Override
    public User findByUsername(final String username) {
        return this.userRepository.findByUsername(username).orElseThrow(
                ()-> new ResourceNotFoundException("User with username %s was not found".formatted(username))
        );
    }

    @Override
    public User create(final User user) {
        user.addRole(Role.ROLE_USER);
        user.addRole(Role.ROLE_ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }
}
