package com.green.jobdone.serviceType.detail;

import com.green.jobdone.serviceType.detail.model.DetailTypeGetReq;
import com.green.jobdone.serviceType.detail.model.DetailTypeGetRes;
import com.green.jobdone.serviceType.detail.model.DetailTypePostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DetailTypeMapper {
    int insDetailType(DetailTypePostReq p);
    List<DetailTypeGetRes> insDetailTypeList(List<DetailTypeGetReq> p);
}
