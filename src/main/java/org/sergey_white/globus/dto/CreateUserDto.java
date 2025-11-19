package org.sergey_white.globus.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Schema(description = "Объект для создания пользователя(User)")
public record CreateUserDto(@Schema(example = "Alex")
                            @NotBlank(message = "Name cannot be empty")
                            String name,

                            @Schema(example = "Craft")
                            @NotBlank(message = "SurName cannot be empty")
                            String surName,

                            @Schema(example = "alex@mail.com")
                            @Email(message = "Email should be valid")
                            String mail) {
}
