package com.project.doday.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.*;

@Getter
public enum ErrorMessage {

    SUCCESS(OK, true,  "요청에 성공하였습니다."),
    ALREADY_SIGNUPED_USER(BAD_REQUEST, false, "이미 회원가입한 유저입니다."),
    WRONG_PASSWORD(NOT_FOUND, false, "비밀번호가 맞지 않습니다."),
    USER_NOT_FOUND(NOT_FOUND, false, "해당 회원을 찾을 수 없습니다."),
    INTERVAL_SERVER_ERROR(INTERNAL_SERVER_ERROR, false,"요청을 처리하는 과정에서 서버가 예상하지 못한 오류가 발생하였습니다.");

    private final int code;
    private final boolean isSuccess;
    private final String message;

    ErrorMessage(HttpStatus code, boolean isSuccess, String message) {
        this.code = code.value();
        this.isSuccess = isSuccess;
        this.message = message;
    }
}
