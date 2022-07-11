package com.neuro.service001useroperations.service;

import com.neuro.service001useroperations.dto.UserDto;
import com.neuro.service001useroperations.dto.enums.Status;
import com.neuro.service001useroperations.mapper.UserMapper;
import com.neuro.service001useroperations.model.Authority;
import com.neuro.service001useroperations.model.User;
import com.neuro.service001useroperations.repository.AuthorityRepository;
import com.neuro.service001useroperations.repository.UserRepository;
import com.neuro.service001useroperations.wrapper.ResponseContainer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.neuro.service001useroperations.security.EncryptionAlgorithm.BCRYPT;

@Slf4j
@Service
@RequiredArgsConstructor
public class MutationServiceImpl implements MutationService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AddressService addressService;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final UserMapper userMapper;

    @Override
    public ResponseContainer<UserDto> create(ResponseContainer<UserDto> response, User user) {
        if (!getByEmail(user.getEmail())) {
            addressService.create(user.getAddress());
            List<Authority> authorities = new ArrayList<>();
            authorities.add(Authority.builder().name("READ")/*.user(user)*/.build());
            authorityRepository.saveAll(authorities);
            user.setAuthorities(authorities);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setMatchedPassword(user.getPassword());
            user.setAlgorithm(BCRYPT);
            userRepository.save(user);
            response.setStatus(Status.USER_CREATED);
            UserDto userDto = userMapper.toDto(user);
            log.info("DTO: {}", userDto);
            response.setDto(userMapper.toDto(user));
            return response;
        }
        log.info("user already exists");
        response.setStatus(Status.USER_ALREADY_EXISTS);
        return response;
    }

    @Override
    public ResponseContainer<UserDto> update(ResponseContainer<UserDto> response, User user) {
        return null;
    }

    @Override
    public ResponseContainer<UserDto> delete(ResponseContainer<UserDto> response, User user) {
        userRepository.deleteById(user.getId());
        response.setStatus(Status.USER_DELETED);
        log.info("USER WITH ID {} DELETED", user.getId());
        return response;
    }

    private boolean getByEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }
}