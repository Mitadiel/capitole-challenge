package com.capitole.entryPoint.rest.advice;

import com.capitole.exception.BrandException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BrandControllerAdvice {
    @ExceptionHandler(BrandException.class)
    public ResponseEntity<String> handleEmptyInput(BrandException emptyInputException){
        return new ResponseEntity<>(emptyInputException.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }
}
