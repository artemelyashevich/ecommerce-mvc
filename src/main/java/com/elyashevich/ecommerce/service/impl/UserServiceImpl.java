package com.elyashevich.ecommerce.service.impl;

import com.elyashevich.ecommerce.entity.Role;
import com.elyashevich.ecommerce.entity.User;
import com.elyashevich.ecommerce.exception.ResourceNotFoundException;
import com.elyashevich.ecommerce.repository.UserRepository;
import com.elyashevich.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public static final String USER_WITH_EMAIL_WAS_NOT_FOUND_EXCEPTION_TEMPLATE = "User with email %s was not found";
    public static final String USER_WITH_USERNAME_WAS_NOT_FOUND_EXCEPTION_TEMPLATE = "User with username %s was not found";
    public static final String USER_WITH_ID_WAS_NOT_FOUND_TEMPLATE_EXCEPTION = "User with id %s was not found";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        log.info("Find all users");

        var users = this.userRepository.findAll();

        log.info("Found {} users", users.size());
        return users;
    }

    @Override
    public User findByEmail(final String email) {
        log.info("Find user by email {}", email);

        var user = this.userRepository.findByEmail(email).orElseThrow(
                () -> {
                    var message = String.format(USER_WITH_EMAIL_WAS_NOT_FOUND_EXCEPTION_TEMPLATE, email);

                    log.warn(message);

                    return new ResourceNotFoundException(message);
                }
        );

        log.info("Found user {}", user);
        return user;
    }

    @Override
    public User findByUsername(final String username) {
        log.info("Find user by email {}", username);

        var user = this.userRepository.findByUsername(username).orElseThrow(
                () -> {
                    var message = String.format(USER_WITH_USERNAME_WAS_NOT_FOUND_EXCEPTION_TEMPLATE, username);

                    log.warn(message);

                    return new ResourceNotFoundException(message);
                }
        );

        log.info("Found user {}", user);
        return user;
    }

    @Override
    public User create(final User user) {
        log.info("Create user {}", user);

        user.addRole(Role.ROLE_USER);
        user.addRole(Role.ROLE_ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var newUser = this.userRepository.save(user);

        log.info("Created new user {}", newUser);
        return newUser;
    }

    @Transactional
    @Override
    public void delete(final Long id) {
        log.info("Delete user {}", id);

        var candidate = this.userRepository.findById(id).orElseThrow(
                () -> {
                    var message = String.format(USER_WITH_ID_WAS_NOT_FOUND_TEMPLATE_EXCEPTION, id);

                    log.warn(message);

                    return new ResourceNotFoundException(message);
                }
        );

        log.info("Deleted user {}", candidate);
        this.userRepository.delete(candidate);
    }
}
