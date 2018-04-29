package com.dell.presentation;

import com.dell.InsertWithIdException;
import com.dell.customers.generated.model.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.invoke.MethodHandles;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @ExceptionHandler(InsertWithIdException.class)
    public ResponseEntity<ErrorDto> handleInsertWithId(InsertWithIdException e) {
        ErrorDto error = new ErrorDto().code(404).message(e.getMessage());
        LOG.debug("InsertWithIdException " + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleInvalidParameter(IllegalArgumentException e) {
        ErrorDto error = new ErrorDto().code(412).message(e.getMessage());
        LOG.debug("IllegalArgumentException " + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.PRECONDITION_FAILED)
                .body(error);
    }

}
