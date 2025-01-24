package com.green.jobdone.config.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.jobdone.config.security.MyUserDetails;
import com.green.jobdone.config.security.MyUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;

@Service
public class TokenProvider {
    private final ObjectMapper objectMapper; //Jackson 라이브러리
    private final JwtConst jwtConst;
    private final SecretKey secretKey;


    public TokenProvider(ObjectMapper objectMapper, JwtConst jwtConst) {
        this.objectMapper = objectMapper;
        this.jwtConst = jwtConst;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConst.getSecret())); //43자 이상
    }

    // JWT 생성
    public String generateAccessToken(JwtUser jwtUser) {
        return generateToken(jwtUser, jwtConst.getAccessTokenExpiry());
    }


    public String generateRefreshToken(JwtUser jwtUser) {
        return generateToken(jwtUser, jwtConst.getRefreshTokenExpiry());
    }


    public String generateToken(JwtUser jwtUser, long tokenValidMillSecond) {
        Date now = new Date();


        return Jwts.builder()
                .header().type(jwtConst.getTokenName())
                .and()

                .issuer(jwtConst.getIssuer())
                .issuedAt(now) //토큰 발행 날짜
                .expiration(new Date(now.getTime() + tokenValidMillSecond))
                .claim(jwtConst.getClaimKey(), makeClaimByUserToString(jwtUser)) // claim 생성, JwtUser 정보 담아줌.


                .signWith(secretKey)
                .compact();
    }


    // 객체>String :직렬화(json 화)
    private String makeClaimByUserToString(JwtUser jwtUser) {
        //객체 자체를 JWT에 담고 싶어서 객체를 직렬화
        //jwtUser에 담고있는 데이터를 JSON형태의 문자열로 변환
        try {
            return objectMapper.writeValueAsString(jwtUser);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


//    public boolean validToken(String token) {
//        try {
//            //JWT 복호화
//            getClaims(token);
//        } catch (Exception e) {
////            return false;
//            throw new CustomException(UserErrorCode.EXPIRED_TOKEN); // 우리가 만든 예외처리 에러코드 발생
//        }
//        return true;
//    }

    //Spring Security에서 인증 처리를 해주어야 한다. 그때 Authentication 객체가 필요.
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = getUserDetailsFromToken(token);  // 인증된 사용자 정보가 담긴 JwtUser 객체가 담긴 UserDetails 인터페이스를 구현한 객체
        return userDetails == null
                ? null
                : new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public JwtUser getJwtUserFromToken(String token) {
        Claims claims = getClaims(token);
        String json = (String)claims.get("signedUser");
        JwtUser jwtUser = null;
        try {
            jwtUser = objectMapper.readValue(json, JwtUser.class); // JSON>객체로 역직렬화(인증된 사용자 정보가 JwtUser 객체에 담김)
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jwtUser;
    }

    public UserDetails getUserDetailsFromToken(String token) {
        JwtUser jwtUser = getJwtUserFromToken(token);
        MyUserDetails userDetails = new MyUserDetails();
        userDetails.setJwtUser(jwtUser);
        return userDetails;
    }

    private Claims getClaims(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }

}
