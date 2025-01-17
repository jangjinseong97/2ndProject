package com.green.jobdone.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEmailCheckReq {
    @Schema(description = "이메일", example = "1234park@naver.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
}
