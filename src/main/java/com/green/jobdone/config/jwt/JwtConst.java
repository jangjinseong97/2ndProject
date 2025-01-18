package com.green.jobdone.config.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

// yaml 을 객체화 해주는 작업

@Getter
@ConfigurationProperties(prefix = "jwt-const") // yaml 에 있는 객체화해줄 const 를 적어준것,
@RequiredArgsConstructor
@ToString
public class JwtConst {
        private final String issuer;
        private final String secret;
        private final String headerSchemaName;
        private final String claimKey;
        private final String tokenName;
        private final String tokenType;
        private final long accessTokenExpiry;
        private final long refreshTokenExpiry;
        private final String refreshTokenCookieName;
        private final int refreshTokenCookieExpiry;
}

