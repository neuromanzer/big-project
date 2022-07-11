package com.neuro.service001useroperations.wrapper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Violation {
    private String className;
    private String propertyName;
    private String message;
    private Object value;
}
