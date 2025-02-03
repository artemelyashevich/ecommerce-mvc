package com.elyashevich.ecommerce.service.impl;

import com.elyashevich.ecommerce.entity.Role;
import com.elyashevich.ecommerce.exception.ResourceNotFoundException;
import com.elyashevich.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String USER_WITH_USERNAME_WAS_NOT_FOUND_EXCEPTION_TEMPLATE = "User with username %s was not found";

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        log.info("Attempting to load user {}", username);

        var user = this.repository.findByUsername(username).orElseThrow(
                ()-> new ResourceNotFoundException(USER_WITH_USERNAME_WAS_NOT_FOUND_EXCEPTION_TEMPLATE.formatted(username))
        );

        var result = new User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(Role::name)
                        .map(SimpleGrantedAuthority::new)
                        .toList()
        );

        log.info("User loaded successfully");
        return result;
    }
}
