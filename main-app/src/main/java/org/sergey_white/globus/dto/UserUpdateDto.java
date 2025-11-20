package org.sergey_white.globus.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;


@Schema(description = "Объект для обновления пользователя(User)")
public record UserUpdateDto(@Schema(example = "Max")
                            @Nullable
                            String name,

                            @Schema(example = "Fish")
                            @Nullable
                            String surName,

                            @Schema(example = "fish@mail.com")
                            @Nullable
                            @Email(message = "Email should be valid")
                            String mail) {
}
