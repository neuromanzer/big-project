package com.neuro.service001useroperations.mapper;

import com.neuro.service001useroperations.dto.UserDto;
import com.neuro.service001useroperations.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toModel(UserDto userDto);

    UserDto toDto(User user);
}
