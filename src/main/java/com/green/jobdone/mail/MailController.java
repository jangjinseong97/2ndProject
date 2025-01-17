package com.green.jobdone.mail;

import com.green.jobdone.common.model.ResultResponse;
import com.green.jobdone.mail.model.MailSendDto;
import com.green.jobdone.mail.model.MemberEmailVerifyReq;
import com.green.jobdone.mail.MailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@Tag(name = "이메일", description = "이메일 인증 과정")
public class MailController {

    private final MailService mailService;

    @ResponseBody
    @PostMapping("email-check") // 이 부분은 각자 바꿔주시면 됩니다.
    @Operation(summary = "이메일 체크")
    public String emailCheck(@RequestBody MailSendDto mailDto) throws MessagingException, UnsupportedEncodingException {
        String authCode = mailService.sendSimpleMessage(mailDto.getEmail());
        return authCode; // Response body에 값을 반환
    }

    @PutMapping("auth-check")
    @Operation(summary = "인증 체크")
    public ResultResponse<Integer> updAuthCheck(@ModelAttribute MemberEmailVerifyReq p) {
        int result = mailService.verifyCode(p);
        return ResultResponse.<Integer>builder()
                .resultMessage(result == 1 ? "인증되었습니다." : "인증되지 않았습니다.")
                .resultData(result)
                .build();
    }
}
