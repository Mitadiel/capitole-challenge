package com.capitole.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 2L;

    private String errorMessage;

    public BusinessException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public BusinessException() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
