package com.green.jobdone.business;

import com.green.jobdone.business.model.BusinessPostSignUpReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessMapper {
    int insBusiness(BusinessPostSignUpReq p);
}
