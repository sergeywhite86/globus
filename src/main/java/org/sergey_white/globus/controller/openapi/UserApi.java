package org.sergey_white.globus.controller.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.sergey_white.globus.dto.CreateUserDto;
import org.sergey_white.globus.dto.UserDto;
import org.sergey_white.globus.dto.UserUpdateDto;
import org.sergey_white.globus.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "API для управления пользователями")
public interface UserApi {

    @Operation(summary = "Создать нового пользователя", description = "Создание нового пользователя в системе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно создан",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Неверные данные пользователя"),
            @ApiResponse(responseCode = "409", description = "Пользователь с такими данными уже существует")
    })
    @PostMapping
    User createUser(@Parameter(description = "Данные для создания пользователя")
                    @RequestBody @Valid CreateUserDto dto);

    @Operation(summary = "Получить пользователя по ID", description = "Получение информации о пользователе по его идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/{id}")
    UserDto getUserById(@Parameter(description = "ID пользователя") @PathVariable Long id);

    @Operation(summary = "Получить всех пользователей", description = "Получение списка всех пользователей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список пользователей успешно получен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class)))
    })
    @GetMapping
    List<UserDto> getAllUsers();

    @Operation(summary = "Обновить пользователя", description = "Частичное обновление данных пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
            @ApiResponse(responseCode = "400", description = "Неверные данные для обновления")
    })
    @PatchMapping("/{id}")
    UserDto updateUser(@Parameter(description = "ID пользователя") @PathVariable Long id,
                       @Parameter(description = "Данные для обновления")
                       @RequestBody @Valid UserUpdateDto updateDto);

    @Operation(summary = "Удалить пользователя по ID", description = "Удаление пользователя по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно удален"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @DeleteMapping("/{id}")
    void deleteUser(@Parameter(description = "ID пользователя") @PathVariable Long id);
}