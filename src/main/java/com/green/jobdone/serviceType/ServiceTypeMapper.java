package com.green.jobdone.serviceType;

import com.green.jobdone.serviceType.detail.model.DetailTypeGetReq;
import com.green.jobdone.serviceType.detail.model.DetailTypeGetRes;
import com.green.jobdone.serviceType.detail.model.DetailTypePostReq;
import com.green.jobdone.serviceType.model.ServiceTypeGetReq;
import com.green.jobdone.serviceType.model.ServiceTypeGetRes;
import com.green.jobdone.serviceType.model.ServiceTypePostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServiceTypeMapper {
    int insServiceType(ServiceTypePostReq p);
    int existServiceType(String serviceTypeName);

    List<ServiceTypeGetRes> getServiceType(ServiceTypeGetReq p);

    // 디테일 타입

    int insDetailType(DetailTypePostReq p);
    int existDetailType(long serviceTypeId,String detailTypeName);

    List<DetailTypeGetRes> getDetailTypeList(DetailTypeGetReq p);

}
