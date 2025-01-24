package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.entity.User;

public interface AuthService {

    void register(User user);

    void login(User user);
}
