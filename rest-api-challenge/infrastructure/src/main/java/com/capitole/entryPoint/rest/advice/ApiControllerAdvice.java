package com.capitole.entryPoint.rest.advice;

import com.capitole.exception.BusinessException;
import com.capitole.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiControllerAdvice {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessInput(BusinessException businessException){
        return new ResponseEntity<>(businessException.getErrorMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEmptyInput(EntityNotFoundException entityNotFoundException){
        return new ResponseEntity<>(entityNotFoundException.getErrorMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullInput(NullPointerException nullPointerException){
        return new ResponseEntity<>(nullPointerException.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
