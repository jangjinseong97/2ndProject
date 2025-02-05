package com.green.jobdone.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoPatchReq {
    @JsonIgnore
    private long userId;
    @Schema(description = "유저 휴대폰번호", example = "01012345678")
    private String phone;
    @Schema(title = "닉네임",example = "홍길동")
    private String name;
    @JsonIgnore
    private String pic;


}

