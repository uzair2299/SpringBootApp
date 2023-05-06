package com.example.SprintBootAppWithSQL.enums;

public enum ErrorMessage {
    DATA_ACCESS_ERROR("Could not save data due to an issue with the data source."),
    CONSTRAINT_VIOLATION_ERROR("Could not save data due to a constraint violation."),
    TRANSACTION_ERROR("Could not save data due to a transaction error."),
    CONFLICT_ERROR("Could not save data due to a conflict."),
    INVALID_INPUT_ERROR("Could not save data due to invalid input data."),
    ILLEGAL_ARGUMENT_ERROR("Could not save data due to an invalid entity state."),
    PERSISTENCE_ERROR("Could not save data due to a persistence error.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
