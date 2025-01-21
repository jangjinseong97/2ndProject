package com.green.jobdone.business;

import com.green.jobdone.business.phone.BusinessPhonePostReq;
import com.green.jobdone.business.model.BusinessDetailPutReq;
import com.green.jobdone.business.pic.BusinessPicDto;
import com.green.jobdone.business.model.BusinessPostSignUpReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessMapper {
    int insBusiness(BusinessPostSignUpReq p);
    int udtBusiness(BusinessDetailPutReq p);

    //businessPhone
    int insBusinessPhone(BusinessPhonePostReq p);
    int existBusinessPhone(long businessId, String phone);
    //pic
    int insBusinessPic(BusinessPicDto p);

    //portfolio

}
