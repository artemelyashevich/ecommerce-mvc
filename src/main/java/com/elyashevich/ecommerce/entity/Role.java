package com.elyashevich.ecommerce.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    Role(String name) {
    }

    @Override
    public String getAuthority() {
        return this.name();
    }
}