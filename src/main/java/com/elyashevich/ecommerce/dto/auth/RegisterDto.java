package com.elyashevich.ecommerce.dto.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record RegisterDto(

        @NotNull(message = "Username must be not null")
        @Length(
                min = 1,
                max = 255,
                message = "Username must be in {min} and {max}"
        )
        String username,

        @NotNull(message = "Full name must be not null")
        @Length(
                min = 2,
                max = 255,
                message = "Full name must be in {min} and {max}"
        )
        String fullName,

        @NotNull(message = "Email must be not null")
        @Length(
                min = 5,
                max = 255,
                message = "Email must be in {min} and {max}"
        )
        @Email(message = "Invalid email  format")
        String email,

        @NotNull(message = "Address must be not null")
        @Length(
                min = 5,
                max = 255,
                message = "Address must be in {min} and {max}"
        )
        String address,

        @NotNull(message = "Password must be not null")
        @Length(
                min = 8,
                max = 255,
                message = "Password must be in {min} and {max}"
        )
        String password
) {
}
