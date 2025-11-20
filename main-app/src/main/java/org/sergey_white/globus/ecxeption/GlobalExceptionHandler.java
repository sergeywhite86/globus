package org.sergey_white.globus.ecxeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppError handleUserNotFound(UserNotFoundException e) {
        return new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(UserIsPresentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public AppError handleUserIsPresent(UserIsPresentException e) {
        return new AppError(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AppError handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return new AppError(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors);
    }

}
