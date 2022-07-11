package com.neuro.service001useroperations.service;

import com.neuro.service001useroperations.dto.UserDto;
import com.neuro.service001useroperations.model.User;
import com.neuro.service001useroperations.wrapper.ResponseContainer;

public interface MutationService {

    ResponseContainer<UserDto> create(ResponseContainer<UserDto> response, User user);

    ResponseContainer<UserDto> update(ResponseContainer<UserDto> response, User user);

    ResponseContainer<UserDto> delete(ResponseContainer<UserDto> response, User user);
}
