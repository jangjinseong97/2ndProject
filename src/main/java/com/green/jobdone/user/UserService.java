package com.green.jobdone.user;

import com.green.jobdone.common.CookieUtils;
import com.green.jobdone.common.MyFileUtils;
import com.green.jobdone.config.jwt.JwtConst;
import com.green.jobdone.config.jwt.JwtUser;
import com.green.jobdone.config.jwt.TokenProvider;
import com.green.jobdone.config.security.AuthenticationFacade;
import com.green.jobdone.mail.MailMapper;
import com.green.jobdone.user.model.*;
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


    public int postUserSignUp(UserSignUpReq p, MultipartFile pic){
        String savedPicName = (pic != null ? myFileUtils.makeRandomFileName(pic) : null);


        String hashedPassword = passwordEncoder.encode(p.getUpw());
        log.info("hashedPassword: {}", hashedPassword);
        p.setUpw(hashedPassword);
        p.setPic(savedPicName);

        int result=mapper.postUserSignUp(p);

        if(pic == null) {
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
        if(res==null||!passwordEncoder.matches(p.getUpw(), res.getUpw())) {
           return null;
        }

        // 예외처리를 하기 전에는 밑에 보이는 것처럼 처리했어야했다.
//        if( res == null ) { //아이디 없음
//            res = new UserSignInRes();
//            res.setMessage("아이디를 확인해 주세요.");
//            return res;
//        } else if(!passwordEncoder.matches(p.getUpw(), res.getUpw())) { //비밀번호가 다를시
//        //} else if( !BCrypt.checkpw(p.getUpw(), res.getUpw()) ) { //비밀번호가 다를시
//            res = new UserSignInRes();
//            res.setMessage("비밀번호를 확인해 주세요.");
//            return res;
//        }

        /*
        JWT 토큰 생성 2개? AccessToken(20분), RefreshToken(15일)
         */
        JwtUser jwtUser = new JwtUser(res.getUserId(),res.getRoles());


        String accessToken = tokenProvider.generateToken(jwtUser, jwtConst.getAccessTokenExpiry());
        String refreshToken = tokenProvider.generateToken(jwtUser,jwtConst.getRefreshTokenExpiry());

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
                .build();
    }

    public int postUserEmailCheck(String email){
        UserSignUpEmailCheckRes res=mapper.postUserEmailCheck(email);

        if(res==null){
            return 1;
        }

        return 0;

    }


    public UserInfoGetRes getUserInfo(long userId){


        return mapper.getUserInfo(userId);

    }










}
