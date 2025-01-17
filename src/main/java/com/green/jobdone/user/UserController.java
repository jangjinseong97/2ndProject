package com.green.jobdone.user;

import com.green.jobdone.common.model.ResultResponse;
import com.green.jobdone.user.model.UserSignUpReq;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("sign-up")
    @Operation(summary = "회원가입")
    public ResultResponse<Integer> postUserSignUp(@Valid @RequestPart UserSignUpReq p, @RequestPart(required = false) MultipartFile pic) {
        int result=userService.postUserSignUp(p,pic);

        return ResultResponse.<Integer>builder()
                .resultMessage("회원가입완료")
                .resultData(result)
                .build();
    }
}
