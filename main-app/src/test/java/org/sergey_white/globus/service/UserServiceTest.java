package org.sergey_white.globus.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sergey_white.globus.dto.CreateUserDto;
import org.sergey_white.globus.dto.UserDto;
import org.sergey_white.globus.dto.UserUpdateDto;
import org.sergey_white.globus.ecxeption.UserIsPresentException;
import org.sergey_white.globus.ecxeption.UserNotFoundException;
import org.sergey_white.globus.entity.User;
import org.sergey_white.globus.mapper.UserMapper;
import org.sergey_white.globus.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @Mock
    KafkaProducerService kafkaProducerService;

    @InjectMocks
    private UserService userService;

    private final Long USER_ID = 1L;

    @Test
    void save_WhenUserNotExists_ShouldSaveUser() {
        CreateUserDto createDto = new CreateUserDto("Ivan", "Ivanov", "ivan@mail.com");
        User user = new User();

        when(repository.findUserByMailEquals("ivan@mail.com")).thenReturn(null);
        when(mapper.toUserFromCreateUserDto(createDto)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);

        User result = userService.save(createDto);

        assertNotNull(result);
        assertEquals(user, result);
        verify(repository).findUserByMailEquals("ivan@mail.com");

    }

    @Test
    void save_WhenUserExists_ShouldThrowException() {

        CreateUserDto createDto = new CreateUserDto("Ivan", "Ivanov", "ivan@mail.com");
        User existingUser = new User();

        when(repository.findUserByMailEquals("ivan@mail.com")).thenReturn(existingUser);

        assertThrows(UserIsPresentException.class, () -> userService.save(createDto));

        verify(repository).findUserByMailEquals("ivan@mail.com");

    }

    @Test
    void getById_WhenUserExists_ShouldReturnUserDto() {

        User user = new User();
        UserDto userDto = new UserDto("Oleg", "Olegov");
        when(repository.findById(USER_ID)).thenReturn(Optional.of(user));
        when(mapper.toUserDtoFromUser(user)).thenReturn(userDto);

        UserDto result = userService.getById(USER_ID);

        assertEquals(userDto, result);
        verify(repository).findById(USER_ID);

    }

    @Test
    void getById_WhenUserNotExists_ShouldThrowException() {

        when(repository.findById(USER_ID)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getById(USER_ID));

        verify(repository).findById(USER_ID);

    }

    @Test
    void getAll_ShouldReturnListOfUserDtos() {

        User user = new User();
        UserDto userDto = new UserDto("Oleg", "Ivanov");
        List<User> users = List.of(user);

        when(repository.findAll()).thenReturn(users);
        when(mapper.toUserDtoFromUser(user)).thenReturn(userDto);

        List<UserDto> result = userService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(userDto, result.getFirst());
        verify(repository).findAll();

    }

    @Test
    void update_WhenUserExists_ShouldUpdateAndReturnUserDto() {

        UserUpdateDto updateDto = new UserUpdateDto("Alina", "Ivanova", "alina@mail.com");
        User user = new User();
        user.setName("Alina");
        user.setSurName("Minina");
        user.setMail("alina@mail.com");

        UserDto updatedUserDto = new UserDto("Alina", "Smith");

        when(repository.findById(USER_ID)).thenReturn(Optional.of(user));
        when(repository.save(user)).thenReturn(user);
        when(mapper.toUserDtoFromUser(user)).thenReturn(updatedUserDto);

        UserDto result = userService.update(USER_ID, updateDto);

        assertNotNull(result);
        assertEquals("Alina", result.name());
        assertEquals("Smith", result.surName());
        verify(repository).findById(USER_ID);
        verify(mapper).updateFromUserUpdateDto(updateDto, user);

    }

    @Test
    void update_WhenUserNotExists_ShouldThrowException() {

        UserUpdateDto updateDto = new UserUpdateDto("Oleg", null, null);
        when(repository.findById(USER_ID)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.update(USER_ID, updateDto));
        verify(repository).findById(USER_ID);

    }

    @Test
    void deleteById_WhenUserExists_ShouldDeleteUser() {

        User user = new User();
        when(repository.findById(USER_ID)).thenReturn(Optional.of(user));

        userService.deleteById(USER_ID);

        verify(repository).findById(USER_ID);
        verify(repository).deleteById(USER_ID);

    }

    @Test
    void deleteById_WhenUserNotExists_ShouldThrowException() {

        when(repository.findById(USER_ID)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.deleteById(USER_ID));
        verify(repository).findById(USER_ID);

    }

}