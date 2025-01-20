package com.green.jobdone.config.jwt;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode //Equals, HashCode 메소드 오버라이딩
public class JwtUser {
    private final long signedUserId;
    private final List<UserRole> roles; //인가(권한)처리 때 사용, ROLE_이름, ROLE_USER, ROLE_ADMIN
}
