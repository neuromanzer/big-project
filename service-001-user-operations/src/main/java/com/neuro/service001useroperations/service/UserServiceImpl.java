package com.neuro.service001useroperations.service;

import com.neuro.service001useroperations.dto.UserDto;
import com.neuro.service001useroperations.dto.enums.Status;
import com.neuro.service001useroperations.model.User;
import com.neuro.service001useroperations.mapper.UserMapper;
import com.neuro.service001useroperations.repository.UserRepository;
import com.neuro.service001useroperations.wrapper.RequestContainer;
import com.neuro.service001useroperations.wrapper.ResponseContainer;
import com.neuro.service001useroperations.wrapper.Violation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.neuro.service001useroperations.dto.enums.Status.EMPTY_REQUEST;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Validator validator;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final MutationService mutationService;

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> dto = new ArrayList<>();
        users.forEach(user -> {
            dto.add(userMapper.toDto(user));
        });
        return dto;
    }

    @Override
    @Transactional
    public ResponseContainer<UserDto> mutate(RequestContainer<UserDto> request) {
        ResponseContainer<UserDto> response = new ResponseContainer<>();
        if (request.getDto() == null) {
            response.setStatus(EMPTY_REQUEST);
            return response;
        }
        User user = userMapper.toModel(request.getDto());
        if (request.getOperationType().toString().equalsIgnoreCase("create") ||
                request.getOperationType().toString().equalsIgnoreCase("update")) {
            List<Violation> violations = getViolations(user);
            if (!violations.isEmpty()) {
                response.setStatus(Status.VALIDATION_ERROR);
                response.setViolations(violations);
                return response;
            }
        }
        return request.getOperationType().mutate(mutationService, response, user);
    }

    private List<Violation> getViolations(User user) {
        Set<ConstraintViolation<User>> constraints = validator.validate(user);
        List<Violation> violations = new ArrayList<>();
        if (!constraints.isEmpty()) {
            log.info("validation failed");
            constraints.forEach(x -> {
                violations.add(Violation.builder()
                        .className(x.getRootBeanClass().getSimpleName())
                        .propertyName(x.getPropertyPath().toString())
                        .message(x.getMessage())
                        .value(x.getInvalidValue())
                        .build());
            });
            return violations;
        }
        return violations;
    }
}