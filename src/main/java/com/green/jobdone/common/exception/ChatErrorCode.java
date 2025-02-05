package com.green.jobdone.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ChatErrorCode implements ErrorCode {
    FAIL_TO_REG(HttpStatus.BAD_REQUEST, "당사자만 입력 가능합니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
