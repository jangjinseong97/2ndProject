package com.green.jobdone.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfoDelReq {
    @JsonIgnore
    private long userId;
    @Schema(description = "유저 비밀번호", example = "1111", requiredMode = Schema.RequiredMode.REQUIRED)
    private String upw;

}
