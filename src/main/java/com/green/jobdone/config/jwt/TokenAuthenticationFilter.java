package com.green.jobdone.config.jwt;

import com.green.jobdone.common.exception.UserErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
@ToString
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("ip Address: {}", request.getRemoteAddr());
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);  //"Bearer 토큰값"
        log.info("authorizationHeader: {}", authorizationHeader);

        String token = getAccessToken(authorizationHeader);// 클라이언트에서 넘어온 Authoriztion header 에 있는 accessToken 을 추출
        log.info("token: {}", token);




        // 토큰이 필요 없는 API 패스
        if (isExcludedPath(request.getRequestURI())) {
            filterChain.doFilter(request, response); // 필터 체인 계속 진행
            return;
        }


        if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
            log.error("Authorization header is missing or invalid");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "토큰이 필요합니다.");
            return; // 400 응답을 반환한 후, 필터 체인 진행을 막음
        }

        if (token != null) {
            try {
                Authentication auth = tokenProvider.getAuthentication(token); // 클라이언트에게 받은 토큰에서 인증된 사용자 정보를 토대로 Authentication 객체 생성
                SecurityContextHolder.getContext().setAuthentication(auth); // SecurityContextHolder에서 Authentication 객체를 관리

            } catch (RuntimeException  e) { // 만료된 토큰일 경우
                log.error("Token expired: {}", e.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, UserErrorCode.EXPIRED_TOKEN.getMessage());
                return; // 401 응답을 반환한 후, 필터 체인 진행을 막음
            } catch (Exception e) {
                log.error("Token validation failed: {}", e.getMessage());
                request.setAttribute("exception", UserErrorCode.INVALID_TOKEN.getMessage()); // 예외를 request에 담아서 나중에 처리할 수 있도록 함
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String authorizationHeader) {
        if(authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }



    private boolean isExcludedPath(String uri) {
        return uri.startsWith("/api/business") || uri.startsWith("/api/product") || uri.startsWith("/api/portfolio")|| uri.startsWith("/api/category")|| uri.startsWith("/api/service")|| uri.startsWith("/api/review")|| uri.startsWith("/api/chat")|| uri.startsWith("/api/room");
    }
}

