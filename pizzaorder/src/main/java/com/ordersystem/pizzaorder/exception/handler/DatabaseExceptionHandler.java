package com.ordersystem.pizzaorder.exception.handler;

import com.ordersystem.pizzaorder.exception.error.ErrorMessage;
import com.ordersystem.pizzaorder.exception.generic.InvalidParameter;
import com.ordersystem.pizzaorder.exception.generic.NotFoundEntity;
import com.ordersystem.pizzaorder.exception.generic.NullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DatabaseExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(NotFoundEntity notFoundEntity) {
        return new ResponseEntity<>(
                new ErrorMessage(HttpStatus.NOT_FOUND.value(), notFoundEntity.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(InvalidParameter invalidParameter) {
        return new ResponseEntity<>(
                new ErrorMessage(HttpStatus.NOT_FOUND.value(), invalidParameter.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(NullException nullException) {
        return new ResponseEntity<>(
                new ErrorMessage(HttpStatus.NOT_FOUND.value(), nullException.getMessage()),
                HttpStatus.NOT_FOUND);
    }

}
