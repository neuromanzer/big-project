package com.neuro.service001useroperations.wrapper;

import com.neuro.service001useroperations.dto.UserDto;
import com.neuro.service001useroperations.model.User;
import com.neuro.service001useroperations.service.MutationService;
import lombok.Data;

@Data
public class RequestContainer<T> {
    private T dto;
    private OperationType operationType;

    public enum OperationType {
        CREATE {
            public ResponseContainer<UserDto> mutate(MutationService userMutationService, ResponseContainer<UserDto> response, User user) {
                return userMutationService.create(response, user);
            }
        },
        UPDATE {
            public ResponseContainer<UserDto> mutate(MutationService userMutationService, ResponseContainer<UserDto> response, User user) {
                return userMutationService.update(response, user);
            }
        },
        DELETE {
            public ResponseContainer<UserDto> mutate(MutationService userMutationService, ResponseContainer<UserDto> response, User user) {
                return userMutationService.delete(response, user);
            }
        };

        public abstract ResponseContainer<UserDto> mutate(MutationService userMutationService, ResponseContainer<UserDto> response, User user);
    }
}
