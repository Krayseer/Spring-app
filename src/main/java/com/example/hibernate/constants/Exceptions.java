package com.example.hibernate.constants;

import com.example.hibernate.exceptions.ServiceException;
import org.springframework.http.HttpStatus;

public class Exceptions {
    public static final ServiceException EXCEPTION_TUTORIAL_ID_NOT_EXISTS = ServiceException
            .builder()
            .code(Code.TUTORIAL_ID_NOT_EXISTS)
            .message("Tutorial with this id does not exist")
            .httpStatus(HttpStatus.BAD_REQUEST)
            .build();
    public static final ServiceException EXCEPTION_COMMENT_ID_NOT_EXISTS = ServiceException
            .builder()
            .code(Code.COMMENT_ID_NOT_EXISTS)
            .message("Comment with this id does not exist")
            .httpStatus(HttpStatus.BAD_REQUEST)
            .build();

    public static final ServiceException EXCEPTION_EMPTY_LIST = ServiceException
            .builder()
            .code(Code.EMPTY_RESULT)
            .message("This tutorial has no comments")
            .httpStatus(HttpStatus.OK)
            .build();
}
