package com.green.jobdone.serviceType;

import com.green.jobdone.serviceType.model.ServiceTypeGetReq;
import com.green.jobdone.serviceType.model.ServiceTypeGetRes;
import com.green.jobdone.serviceType.model.ServiceTypePostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServiceTypeMapper {
    int insCategory(ServiceTypePostReq p);
    int existCategory(String serviceTypeName);

    List<ServiceTypeGetRes> getCategory(ServiceTypeGetReq p);
}
