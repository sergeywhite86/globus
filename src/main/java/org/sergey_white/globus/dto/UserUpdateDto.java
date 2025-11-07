package org.sergey_white.globus.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Объект для обновления пользователя(User)")
public record UserUpdateDto(@Schema(example = "Max")
                            @NotBlank(message = "Name cannot be empty")
                            @Nullable
                            String name,

                            @Schema(example = "Fish")
                            @NotBlank(message = "Surname cannot be empty")
                            @Nullable
                            String surName,

                            @Schema(example = "fish@mail.com")
                            @Nullable @Email(message = "Email should be valid")
                            String mail) {
}
