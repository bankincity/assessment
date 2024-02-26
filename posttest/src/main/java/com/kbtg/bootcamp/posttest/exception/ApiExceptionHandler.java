package com.kbtg.bootcamp.posttest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;


@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ApiExceptionResponse> handleNotFoundException(NotFoundException notFoundException) {

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                notFoundException.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<ApiExceptionResponse> handleNotFoundException(BadRequestException badRequestException) {

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                badRequestException.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InternalServiceException.class})
    public ResponseEntity<ApiExceptionResponse> handleInternalServiceException(InternalServiceException internalServiceException){

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                internalServiceException.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
