package com.neuro.service001useroperations.dto.enums;

public enum RequestOperation {
    CREATE("create"),
    UPDATE("update"),
    DELETE("delete");

    private final String operation;

    RequestOperation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
