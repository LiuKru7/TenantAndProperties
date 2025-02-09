package com.TenantAndProperties.handler;

import com.TenantAndProperties.Exceptions.TenantOrPropertyNotFoundException;
import com.TenantAndProperties.dto.ErrorResponse;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> tenantOrPropertyNotFoundExceptionHandler(TenantOrPropertyNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                new Timestamp(System.currentTimeMillis())
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                errorMessage,
                new Timestamp(System.currentTimeMillis())
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
