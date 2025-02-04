package com.green.jobdone.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements ErrorCode{
    FAIL_TO_DEL(HttpStatus.BAD_REQUEST, "리뷰 삭제에 실패하였습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
