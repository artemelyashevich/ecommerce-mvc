package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.entity.User;

import java.util.List;

/**
 * The UserService interface provides methods for managing users in the e-commerce system.
 */
public interface UserService {

    /**
     * Finds all users.
     *
     * @return a list of all users
     */
    List<User> findAll();

    /**
     * Finds a user by their email.
     *
     * @param email the email of the user to find
     * @return the found user, or null if not found
     */
    User findByEmail(final String email);

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user to find
     * @return the found user, or null if not found
     */
    User findByUsername(final String username);

    /**
     * Creates a new user.
     *
     * @param user the user to create
     * @return the created user
     */
    User create(final User user);

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    void delete(final Long id);

    /**
     * Finds a user by their id.
     *
     * @param id the username of the user to find
     * @return the found user, or null if not found
     */
    User findById(final Long id);
}
