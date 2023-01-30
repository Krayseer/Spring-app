package com.example.hibernate.exceptions.error;

import com.example.hibernate.constants.Code;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {
    private final Code code;
    private final String message;
}
