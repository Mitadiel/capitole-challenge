package com.capitole.exception;

public class BrandException extends RuntimeException {

    private static final long serialVersionUID = 2L;

    private String errorMessage;

    public BrandException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public BrandException() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
