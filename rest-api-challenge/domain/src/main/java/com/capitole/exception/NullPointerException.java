package com.capitole.exception;

public class NullPointerException extends RuntimeException {

    private static final long serialVersionUID = 2L;

    private String errorMessage;

    public NullPointerException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public NullPointerException() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
