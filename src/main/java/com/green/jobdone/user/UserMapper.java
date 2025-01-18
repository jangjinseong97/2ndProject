package com.green.jobdone.user;

import com.green.jobdone.user.model.UserSignInReq;
import com.green.jobdone.user.model.UserSignInResDto;
import com.green.jobdone.user.model.UserSignUpReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int postUserSignUp(UserSignUpReq p);
    UserSignInResDto postUserSignIn(String email);
}
