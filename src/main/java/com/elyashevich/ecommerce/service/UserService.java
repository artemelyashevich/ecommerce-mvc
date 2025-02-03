package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findByEmail(final String email);

    User findByUsername(final String username);

    User create(final User user);

    void delete(final Long id);
}
