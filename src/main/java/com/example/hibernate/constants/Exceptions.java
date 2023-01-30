package com.example.hibernate.constants;

import com.example.hibernate.exceptions.ServiceException;
import org.springframework.http.HttpStatus;

public class Exceptions {
    public static final ServiceException TUTORIAL_ID_NOT_EXISTS = ServiceException
            .builder()
            .code(Code.TUTORIAL_ID_NOT_EXISTS)
            .message("Туториал с таким id не существует")
            .httpStatus(HttpStatus.BAD_REQUEST)
            .build();
    public static final ServiceException COMMENT_ID_NOT_EXISTS = ServiceException
            .builder()
            .code(Code.COMMENT_ID_NOT_EXISTS)
            .message("Комментарий с таким id не существует")
            .httpStatus(HttpStatus.BAD_REQUEST)
            .build();;
}
