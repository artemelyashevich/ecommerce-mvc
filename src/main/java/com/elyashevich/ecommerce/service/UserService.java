package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.entity.User;

public interface UserService {

    User findByEmail(String email);

    User create(User user);
}
