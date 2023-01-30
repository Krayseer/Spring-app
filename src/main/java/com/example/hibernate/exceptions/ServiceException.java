package com.example.hibernate.exceptions;

import com.example.hibernate.constants.Code;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ServiceException extends RuntimeException{
    private final Code code;
    private final String message;
    private final HttpStatus httpStatus;
}
