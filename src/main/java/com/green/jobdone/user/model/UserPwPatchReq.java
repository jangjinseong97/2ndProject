package com.green.jobdone.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPwPatchReq {
    @JsonIgnore
    private long userId;
    @Schema(description = "기존 비밀번호", example = "1111")
    private String currentPassword;
    @Schema(description = "신규 비밀번호", example = "2222")
    private String newPassword;
    @Schema(description = "인증된 이메일")
    private String email;

    @JsonIgnore
    private String message;

}
