package com.elyashevich.ecommerce.dto;

import com.elyashevich.ecommerce.entity.Role;

import java.util.Set;

public record UserDto(
        Long id,
        String username,
        String fullName,
        String email,
        String address,
        Set<Role> roles
) {
}
