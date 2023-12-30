package com.capitole.entryPoint.rest.advice;

import com.capitole.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiControllerAdvice {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleEmptyInput(BusinessException emptyInputException){
        return new ResponseEntity<>(emptyInputException.getErrorMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
