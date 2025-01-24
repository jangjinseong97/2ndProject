package com.green.jobdone.user;

import com.green.jobdone.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int postUserSignUp(UserSignUpReq p);
    boolean checkEmailExists(String email);
    UserSignInResDto postUserSignIn(String email);
    UserSignUpEmailCheckRes postUserEmailCheck(String email);
    UserInfoGetRes getUserInfo(long userId);
    int updateUserInfo(UserInfoPatchReq p);
    String selectInfoPwUser(long userId);
    int deleteUser(UserInfoDelReq p);
    int updatePassword(UserPwPatchReq p);
    int updatePasswordThEmail(UserPwPatchReq p);

}
