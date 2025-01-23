package com.green.jobdone.business;

import com.green.jobdone.business.model.BusinessStatePutReq;
import com.green.jobdone.business.model.get.BusinessGetReq;
import com.green.jobdone.business.model.get.BusinessGetRes;
import com.green.jobdone.business.phone.BusinessPhonePostReq;
import com.green.jobdone.business.model.BusinessDetailPutReq;
import com.green.jobdone.business.pic.BusinessPicDto;
import com.green.jobdone.business.model.BusinessPostSignUpReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusinessMapper {
    int insBusiness(BusinessPostSignUpReq p); //
    int existBusinessNum(String businessNum);
    int udtBusiness(BusinessDetailPutReq p);

    //businessPhone
    int insBusinessPhone(BusinessPhonePostReq p);
    int existBusinessPhone(long businessId, String phone);
    //pic
    int insBusinessPic(BusinessPicDto p);
    int putBusinessPic(long businessPicId);

    //state
    int putBusinessState(BusinessStatePutReq p);

    //get
    List<BusinessGetRes> selAllBusiness(BusinessGetReq p);





    //portfolio



}
