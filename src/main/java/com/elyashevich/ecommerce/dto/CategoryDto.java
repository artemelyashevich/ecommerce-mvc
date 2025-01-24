package com.elyashevich.ecommerce.dto;

import jakarta.validation.constraints.NotNull;

public record CategoryDto(

        @NotNull(message = "Name must not be null")
        String name
) {
}
