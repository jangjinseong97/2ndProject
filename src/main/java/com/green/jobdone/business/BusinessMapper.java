package com.green.jobdone.business;

import com.green.jobdone.business.model.BusinessLogoPatchReq;
import com.green.jobdone.business.model.BusinessStatePutReq;
import com.green.jobdone.business.model.get.BusinessGetOneRes;
import com.green.jobdone.business.model.get.BusinessGetReq;
import com.green.jobdone.business.model.get.BusinessGetRes;
import com.green.jobdone.business.phone.BusinessPhonePostReq;
import com.green.jobdone.business.model.BusinessDetailPutReq;
import com.green.jobdone.business.pic.BusinessOnePicsGetReq;
import com.green.jobdone.business.pic.BusinessOnePicsGetRes;
import com.green.jobdone.business.pic.BusinessPicDto;
import com.green.jobdone.business.model.BusinessPostSignUpReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusinessMapper {
    int insBusiness(BusinessPostSignUpReq p); //
    int existBusinessNum(String businessNum);
    int udtBusiness(BusinessDetailPutReq p);
    int udtBusinessLogo(BusinessLogoPatchReq p);
    //businessPhone
    int insBusinessPhone(BusinessPhonePostReq p);
    int existBusinessPhone(long businessId, String phone);
    //pic
    int insBusinessPic(BusinessPicDto p);
    int putBusinessPic(long businessPicId);
    List<BusinessOnePicsGetRes> getBusinessPicList(BusinessOnePicsGetReq p);

    //state
    int putBusinessState(BusinessStatePutReq p);

    //get 하 내일하자 --> 완
    List<BusinessGetRes> selAllBusiness(BusinessGetReq p);

    BusinessGetOneRes selOneBusiness(long businessId);




    //portfolio



}
