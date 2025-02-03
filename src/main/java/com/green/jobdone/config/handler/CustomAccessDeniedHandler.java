package com.green.jobdone.config.handler;

import com.green.jobdone.common.exception.UserErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("403 Forbidden - 접근이 거부되었습니다.");

        // 403 응답 반환
        response.sendError(HttpServletResponse.SC_FORBIDDEN, UserErrorCode.FORBIDDEN_ACCESS.getMessage());
    }
}