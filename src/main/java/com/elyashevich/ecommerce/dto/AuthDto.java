package com.elyashevich.ecommerce.dto;


public record AuthDto(


        String username,
        String fullName,
        String email,
        String address,
        String password
) {
}
