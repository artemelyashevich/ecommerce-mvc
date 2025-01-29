package com.elyashevich.ecommerce.dto;

import jakarta.validation.constraints.NotNull;

public record CategoryDto(

        Long id,

        @NotNull(message = "Name must not be null")
        String name
) {
}
