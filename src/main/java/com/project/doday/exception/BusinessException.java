package com.project.doday.exception;

import com.project.doday.dto.ErrorMessage;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    private final ErrorMessage errorMessage;

    public BusinessException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}