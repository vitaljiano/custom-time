package com.example.time.demo.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
@Data
@AllArgsConstructor
public class CustomException {
    private String message;
    private HttpStatus httpStatus;
    private ZonedDateTime zonedDateTime;
}
