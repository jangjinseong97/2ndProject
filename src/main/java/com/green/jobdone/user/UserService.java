package com.green.jobdone.user;

import com.green.jobdone.common.MyFileUtils;
import com.green.jobdone.user.model.UserSignUpReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    private final MyFileUtils myFileUtils;


    public int postUserSignUp(UserSignUpReq p, MultipartFile pic){
        String savedPicName = (pic != null ? myFileUtils.makeRandomFileName(pic) : null);



        return  0;



    }










}
