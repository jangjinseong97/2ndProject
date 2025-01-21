package com.green.jobdone.user;

import com.green.jobdone.common.model.ResultResponse;
import com.green.jobdone.user.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "유저")
public class UserController {
    private final UserService service;



    @PostMapping(value = "sign-up")
        @Operation(summary = "회원가입")
        public ResultResponse<Integer> postUserSignUp(@Valid @RequestPart UserSignUpReq p,  @RequestPart(required = false) MultipartFile pic) {
            int result = service.postUserSignUp(p, pic);

            return ResultResponse.<Integer>builder()
                    .resultMessage("회원가입완료")
                .resultData(result)
                .build();
    }


    @PostMapping("sign-in")
    @Operation(summary = "로그인")
    public ResultResponse<UserSignInRes> postUserSignIn(@RequestBody UserSignInReq p, HttpServletResponse response) {
        UserSignInRes res = service.postUserSignIn(p, response);
        return ResultResponse.<UserSignInRes>builder()
                .resultMessage("로그인 성공")
                .resultData(res)
                .build();
    }


    @PostMapping("email")
    @Operation(summary = "이메일 중복 체크")
    public ResultResponse<Integer> postUserEmailCheck(@RequestBody UserEmailCheckReq p) {
        int result = service.postUserEmailCheck(p.getEmail());

        return ResultResponse.<Integer>builder()
                .resultData(result)
                .resultMessage(result == 0 ? "존재하는 이메일 입니다" : "이메일 인증해주세요")
                .build();

    }


    @GetMapping("info")
    @Operation(summary = "회원정보 조회")
    public ResultResponse<UserInfoGetRes> getUserInfo(@RequestParam long userId){
        UserInfoGetRes res= service.getUserInfo(userId);

        return ResultResponse.<UserInfoGetRes>builder()
                .resultMessage(res==null?"회원정보가 없습니다":"회원 정보 조회 완료")
                .resultData(res)
                .build();
    }
}

