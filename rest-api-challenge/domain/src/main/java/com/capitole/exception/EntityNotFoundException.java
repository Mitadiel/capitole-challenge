package com.capitole.exception;

public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2L;

    private String errorMessage;

    public EntityNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public EntityNotFoundException() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
