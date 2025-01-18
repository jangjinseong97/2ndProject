package com.green.jobdone.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSignUpReq {
    @JsonIgnore
    private long userId;
    @JsonIgnore
    private String pic;

    @Size(min=3,max=40,message="이메일은 3~40자 사이만 가능합니다.")
    @Schema(description = "유저 이메일", example = "1234park@naver.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Size(min=4, max=50, message = "비밀번호는 4~50자 사이만 가능합니다.")
    @Schema(description = "유저 비밀번호", example = "1111", requiredMode = Schema.RequiredMode.REQUIRED)
    private String upw;
    @Schema(description = "유저 닉네임", example = "홍길동")
    private String nickName;
    @Schema(description = "유저 휴대폰번호", example = "01012345678")
    private String phone;

}
