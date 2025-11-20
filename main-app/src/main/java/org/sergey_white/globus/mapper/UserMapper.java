package org.sergey_white.globus.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.sergey_white.globus.dto.CreateUserDto;
import org.sergey_white.globus.dto.UserDto;
import org.sergey_white.globus.dto.UserUpdateDto;
import org.sergey_white.globus.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDtoFromUser(User user);
    User toUserFromCreateUserDto(CreateUserDto dto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromUserUpdateDto(UserUpdateDto userDto, @MappingTarget User user);

}
