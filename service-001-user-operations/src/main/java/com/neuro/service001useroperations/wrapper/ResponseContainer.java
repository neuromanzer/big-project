package com.neuro.service001useroperations.wrapper;

import com.neuro.service001useroperations.dto.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseContainer<T> {
    private Status status;
    private T dto;
    private List<Violation> violations;
}
