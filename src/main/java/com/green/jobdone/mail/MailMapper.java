package com.green.jobdone.mail;

import com.green.jobdone.mail.model.MailCheckDto;
import com.green.jobdone.mail.model.MemberEmailVerifyReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MailMapper {
    void insAuthInfo (MailCheckDto dto);
    MailCheckDto selAuthInfo (String email);
    int updAuthCheck(MemberEmailVerifyReq p);
    Integer selAuthCheck(String email);
    void delAuthInfo(String email);
}
