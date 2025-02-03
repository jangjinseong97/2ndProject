package com.green.jobdone.user;

import com.green.jobdone.common.CookieUtils;
import com.green.jobdone.common.MyFileUtils;
import com.green.jobdone.common.PicUrlMaker;
import com.green.jobdone.config.jwt.JwtConst;
import com.green.jobdone.config.jwt.JwtUser;
import com.green.jobdone.config.jwt.TokenProvider;
import com.green.jobdone.config.security.AuthenticationFacade;
import com.green.jobdone.mail.MailMapper;
import com.green.jobdone.user.model.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final MailMapper mailMapper;
    private final UserMapper mapper;
    private final MyFileUtils myFileUtils;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final CookieUtils cookieUtils;
    private final AuthenticationFacade authenticationFacade;
    private final JwtConst jwtConst;


    public int postUserSignUp(UserSignUpReq p, MultipartFile pic) {
        String existsEmail=mapper.checkEmailExists(p.getEmail());

        if(existsEmail!=null) {
            throw new  IllegalArgumentException("이미 등록된 이메일입니다.");
        }

        String savedPicName = (pic != null ? myFileUtils.makeRandomFileName(pic) : null);


        String hashedPassword = passwordEncoder.encode(p.getUpw());
        log.info("hashedPassword: {}", hashedPassword);
        p.setUpw(hashedPassword);
        p.setPic(savedPicName);

        int result = mapper.postUserSignUp(p);

        if (pic == null) {
            mailMapper.delAuthInfo(p.getEmail());
            return result;
        }

        // 저장 위치 만든다.
        // middlePath = user/${userId}
        // filePath = user/${userId}/${savedPicName}
        long userId = p.getUserId(); //userId를 insert 후에 얻을 수 있다.
        String middlePath = String.format("user/%d", userId);
        myFileUtils.makeFolders(middlePath);
        log.info("middlePath: {}", middlePath);
        String filePath = String.format("%s/%s", middlePath, savedPicName);
        try {
            myFileUtils.transferTo(pic, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mailMapper.delAuthInfo(p.getEmail());

        return result;

    }

    public UserSignInRes postUserSignIn(UserSignInReq p, HttpServletResponse response) {
        UserSignInResDto res = mapper.postUserSignIn(p.getEmail()); // email 에 해당하는 유저 res 가져오기
//        if(res==null||!passwordEncoder.matches(p.getUpw(), res.getUpw())) {
//           return null;
//        }

        //예외처리를 하기 전에는 밑에 보이는 것처럼 처리했어야했다.
        if (res == null) { //아이디 없음
            return UserSignInRes.builder()
                    .message("이메일을 확인해주세요")
                    .build();

        } else if (!passwordEncoder.matches(p.getUpw(), res.getUpw())) { //비밀번호가 다를시
            //} else if( !BCrypt.checkpw(p.getUpw(), res.getUpw()) ) { //비밀번호가 다를시
            return UserSignInRes.builder()
                    .message("비밀번호를 확인해주세요")
                    .build();
        }

        /*
        JWT 토큰 생성 2개? AccessToken(20분), RefreshToken(15일)
         */
        JwtUser jwtUser = new JwtUser(res.getUserId(), res.getRoles());


        String accessToken = tokenProvider.generateAccessToken(jwtUser);
        String refreshToken = tokenProvider.generateRefreshToken(jwtUser);

        log.info("accessToken: {}", accessToken);
        log.info("refreshToken: {}", refreshToken);

        //refreshToken은 쿠키에 담는다.

        cookieUtils.setCookie(response, "refreshToken", refreshToken, jwtConst.getRefreshTokenCookieExpiry());

        return UserSignInRes
                .builder()
                .email(res.getEmail())
                .type(res.getType())
                .userId(res.getUserId())
                .pic(res.getPic())
                .accessToken(accessToken)
                .name(res.getName())
                .message("로그인 성공")
                .build();
    }

    public int postUserEmailCheck(String email) {
        UserSignUpEmailCheckRes res = mapper.postUserEmailCheck(email);

        if (res == null) {
            return 1;
        }

        return 0;

    }


    public UserInfoGetRes getUserInfo(long userId) {

        UserInfoGetRes res=mapper.getUserInfo(userId);


        res.setPic(PicUrlMaker.makePicUserUrl(userId,res.getPic()));

        return res;


    }

    public String postUserAccessToken(HttpServletRequest req) {

        Cookie cookie = cookieUtils.getCookie(req, "refreshToken");

        if (cookie != null) {
            String refreshToken = cookie.getValue();
            if (refreshToken != null) {

                JwtUser jwtUser = tokenProvider.getJwtUserFromToken(refreshToken);

                String accessToken = tokenProvider.generateAccessToken(jwtUser);

                return accessToken;
            }
        }

        return null;
    }

    public int updateUserInfo(UserInfoPatchReq p, MultipartFile pic) {

        if (pic == null) {
            int result = mapper.updateUserInfo(p);
            return result;
        }

        String savedPicName = myFileUtils.makeRandomFileName(pic);

        p.setPic(savedPicName);

        int result = mapper.updateUserInfo(p);

        String middlePath = String.format("user/%d", p.getUserId());


        myFileUtils.deleteFile(middlePath);


        String filePath = String.format("%s/%s", middlePath, savedPicName);

        try {
            myFileUtils.transferTo(pic, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }


    public int deleteUser(UserInfoDelReq p) {

        String pw = mapper.selectInfoPwUser(p.getUserId());

        if (pw == null || !passwordEncoder.matches(p.getUpw(), pw)) {
            return 0;
        }

        int result = mapper.deleteUser(p);


        String deletePath = String.format("%s/user/%d", myFileUtils.getUploadPath(), p.getUserId());
        myFileUtils.deleteFolder(deletePath, true);


        return result;

    }

    public UserPwPatchRes updatePassword(UserPwPatchReq p) {

        // 1. 사용자가 현재 비밀번호를 알고 있는 경우
        if (p.getCurrentPassword() != null) {
            String pw = mapper.selectInfoPwUser(p.getUserId());

            // 비밀번호 일치 여부 체크
            if (pw == null || !passwordEncoder.matches(p.getCurrentPassword(), pw)) {
                return UserPwPatchRes.builder()
                        .message("비밀번호가 일치하지 않습니다.")
                        .result(0)
                        .build();
            }

            // 비밀번호 해싱
            String hashedPw = passwordEncoder.encode(p.getNewPassword());
            p.setNewPassword(hashedPw);

            // 비밀번호 변경
            int result = mapper.updatePassword(p);

            return UserPwPatchRes.builder()
                    .message("비밀번호 변경 완료")
                    .result(result)
                    .build();
        }

        // 2. 사용자가 현재 비밀번호를 모를 경우, 이메일 인증을 통한 비밀번호 변경
        Integer authCheck = mailMapper.selAuthCheck(p.getEmail());

        // 인증된 이메일인지 체크
        if (authCheck != null && authCheck == 1) {
            String hashedPw = passwordEncoder.encode(p.getNewPassword());
            p.setNewPassword(hashedPw);

            // 이메일을 통한 비밀번호 변경
            int result = mapper.updatePasswordThEmail(p);

            mailMapper.delAuthInfo(p.getEmail());

            return UserPwPatchRes.builder()
                    .message("비밀번호 변경 완료")
                    .result(result)
                    .build();
        } else if (authCheck == null) {
            return UserPwPatchRes.builder()
                    .message("인증된 이메일이 아닙니다.")
                    .result(0)
                    .build();
        } else {
            return UserPwPatchRes.builder()
                    .message("인증된 이메일이 아닙니다.")
                    .result(0)
                    .build();
        }
    }

}
