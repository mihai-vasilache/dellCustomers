package com.dell.presentation;

import com.dell.InsertWithIdException;
import com.dell.customers.generated.model.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(InsertWithIdException.class)
    public ResponseEntity<ErrorDto> handleException(InsertWithIdException e) {
        ErrorDto error = new ErrorDto().code(404).message(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

}
