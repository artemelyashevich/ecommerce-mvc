package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.entity.User;

/**
 * The AuthService interface provides methods for registering and logging in users.
 */
public interface AuthService {

    /**
     * Registers a new user in the system.
     *
     * @param user the user to be registered
     */
    void register(final User user);

    /**
     * Logs in an existing user.
     *
     * @param user the user to be logged in
     */
    void login(final User user);
}
