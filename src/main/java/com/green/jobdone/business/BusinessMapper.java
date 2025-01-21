package com.green.jobdone.business;

import com.green.jobdone.business.model.post.BusinessTelPostReq;
import com.green.jobdone.business.model.udt.BusinessDetailPutReq;
import com.green.jobdone.business.model.post.BusinessPicDto;
import com.green.jobdone.business.model.post.BusinessPostSignUpReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessMapper {
    int insBusiness(BusinessPostSignUpReq p);
    int udtBusiness(BusinessDetailPutReq p);

    //businessNum
    int insBusinessTel(BusinessTelPostReq p);

    //pic
    int insBusinessPic(BusinessPicDto p);

    //portfolio

}
