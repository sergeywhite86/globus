package org.sergey_white.globus.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Объект для создания пользователя(User)")
public record CreateUserDto(@Schema(example = "Alex")
                            @NotBlank(message = "Name cannot be empty")
                            @NotNull(message = "Name cannot be null")
                            String name,

                            @Schema(example = "Craft")
                            @NotBlank(message = "SurName cannot be empty")
                            @NotNull(message = "Surname cannot be null")
                            String surName,

                            @Schema(example = "alex@mail.com")
                            @NotNull(message = "Email cannot be null")
                            @Email(message = "Email should be valid")
                            String mail) {
}
