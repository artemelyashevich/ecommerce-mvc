package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.entity.User;

public interface UserService {

    User findByEmail(final String email);

    User findByUsername(final String username);

    User create(final User user);
}
