package com.neuro.service001useroperations.service;

import com.neuro.service001useroperations.dto.UserDto;
import com.neuro.service001useroperations.wrapper.RequestContainer;
import com.neuro.service001useroperations.wrapper.ResponseContainer;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    ResponseContainer<UserDto> mutate(RequestContainer<UserDto> request);
}
