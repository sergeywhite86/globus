package org.sergey_white.globus.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.sergey_white.globus.controller.openapi.UserApi;
import org.sergey_white.globus.dto.CreateUserDto;
import org.sergey_white.globus.dto.UserDto;
import org.sergey_white.globus.dto.UserUpdateDto;
import org.sergey_white.globus.entity.User;
import org.sergey_white.globus.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
@Validated
public class UserController implements UserApi {

   private final UserService service;

   @PostMapping
    public User createUser(@RequestBody @Valid CreateUserDto dto){
       return service.save(dto);
   }

   @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id){
       return service.getById(id);
   }

   @GetMapping
    public List<UserDto> getAllUsers(){
      return service.getAll();
   }

    @PatchMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDto updateDto) {
        return service.update(id, updateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
       service.deleteById(id);
    }

}
