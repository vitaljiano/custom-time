package com.example.time.demo.demo.controller;

import com.example.time.demo.demo.exception.UnknownException;
import com.example.time.demo.demo.exception.CustomException;
import com.example.time.demo.demo.exception.FieldIsEmpty;
import com.example.time.demo.demo.exception.TimeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UnknownException.class)
    public ResponseEntity<Object> handleCustomException(UnknownException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        CustomException customException = new CustomException(
                e.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Europe/Kiev"))
        );
        return new ResponseEntity<>(customException, httpStatus);
    }

    @ExceptionHandler(TimeNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(TimeNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        CustomException customException = new CustomException(
                e.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Europe/Kiev"))
        );
        return new ResponseEntity<>(customException, httpStatus);
    }
    @ExceptionHandler(FieldIsEmpty.class)
    public ResponseEntity<Object> handleFieldIsEmpty(FieldIsEmpty e) {
        HttpStatus httpStatus = HttpStatus.EXPECTATION_FAILED;
        CustomException customException = new CustomException(
                e.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Europe/Kiev"))
        );
        return new ResponseEntity<>(customException, httpStatus);
    }

}
