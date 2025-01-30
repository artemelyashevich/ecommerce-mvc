package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.entity.User;

public interface AuthService {

    void register(final User user);

    void login(final User user);
}
