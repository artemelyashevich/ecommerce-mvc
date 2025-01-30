package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.entity.User;

public interface UserService {

    User findByEmail(String email);

    User findByUsername(String username);

    User create(User user);
}
