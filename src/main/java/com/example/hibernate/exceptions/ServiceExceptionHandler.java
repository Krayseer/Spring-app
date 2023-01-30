package com.example.hibernate.exceptions;

import com.example.hibernate.constants.Code;
import com.example.hibernate.exceptions.error.Error;
import com.example.hibernate.exceptions.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ServiceExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> noSuchElementException(ServiceException ex){
        log.error("Ошибка на стороне клиента: " + ex.getMessage());
        return new ResponseEntity<>(ErrorResponse
                .builder()
                .error(Error
                        .builder()
                        .code(ex.getCode())
                        .message(ex.getMessage())
                        .build())
                .build(), ex.getHttpStatus());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> someExceptions(Exception ex){
        log.error("Необработанная ошибка: " + ex.getMessage());
        return new ResponseEntity<>(ErrorResponse
                .builder()
                .error(Error
                        .builder()
                        .code(Code.SERVER_EXCEPTION)
                        .message("Необработанная ошибка на стороне сервера")
                        .build())
                .build(), HttpStatus.BAD_REQUEST);
    }
}
