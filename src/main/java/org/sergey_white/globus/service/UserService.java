package org.sergey_white.globus.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.sergey_white.globus.dto.CreateUserDto;
import org.sergey_white.globus.dto.UserDto;
import org.sergey_white.globus.dto.UserUpdateDto;
import org.sergey_white.globus.ecxeption.UserIsPresentException;
import org.sergey_white.globus.ecxeption.UserNotFoundException;
import org.sergey_white.globus.entity.User;
import org.sergey_white.globus.mapper.UserMapper;
import org.sergey_white.globus.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Transactional
    public User save(CreateUserDto dto) {

        if (repository.findUserByMailEquals(dto.mail()) != null) {
            throw new UserIsPresentException(dto.mail());
        }
        return repository.save(mapper.toUserFromCreateUserDto(dto));

    }

    public UserDto getById(Long id) {

        return repository.findById(id)
                .map(mapper::toUserDtoFromUser)
                .orElseThrow(() -> new UserNotFoundException(id));

    }

    public List<UserDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::toUserDtoFromUser)
                .toList();
    }

    @Transactional
    public UserDto update(Long id, UserUpdateDto updateDto) {

        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        mapper.updateFromUserUpdateDto(updateDto, user);
        User updatedUser = repository.save(user);
        return mapper.toUserDtoFromUser(updatedUser);

    }

    @Transactional
    public void deleteById(Long id) {

        repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        repository.deleteById(id);

    }

}


