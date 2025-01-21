package com.green.jobdone.user;

import com.green.jobdone.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int postUserSignUp(UserSignUpReq p);
    UserSignInResDto postUserSignIn(String email);
    UserSignUpEmailCheckRes postUserEmailCheck(String email);
    UserInfoGetRes getUserInfo(long userId);
}
