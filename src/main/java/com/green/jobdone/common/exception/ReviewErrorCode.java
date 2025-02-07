package com.green.jobdone.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements ErrorCode{
    FAIL_TO_REG(HttpStatus.INTERNAL_SERVER_ERROR, "리뷰 등록에 실패하였습니다."),
    FAIL_TO_REG_COMMENT(HttpStatus.INTERNAL_SERVER_ERROR, "리뷰 댓글 등록에 실패하였습니다."),
    FAIL_TO_UPD(HttpStatus.BAD_REQUEST, "리뷰 수정에 실패하였습니다."),
    FAIL_TO_UPD_COMMENT(HttpStatus.BAD_REQUEST, "리뷰 댓글 수정에 실패하였습니다."),
    FAIL_TO_DEL(HttpStatus.BAD_REQUEST, "리뷰 삭제에 실패하였습니다."),
    FAIL_TO_DEL_COMMENT(HttpStatus.BAD_REQUEST, "리뷰 댓글 삭제에 실패하였습니다."),
    FAIL_TO_REG_COMMENT_EXIST(HttpStatus.BAD_REQUEST, "리뷰 댓글이 이미 달려있습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
